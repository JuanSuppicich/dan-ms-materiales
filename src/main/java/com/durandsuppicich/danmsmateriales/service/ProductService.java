package com.durandsuppicich.danmsmateriales.service;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import com.durandsuppicich.danmsmateriales.exception.product.NameNotFoundException;
import com.durandsuppicich.danmsmateriales.exception.product.ProductIdNotFoundException;
import com.durandsuppicich.danmsmateriales.repository.IOrderItemJpaRepository;
import com.durandsuppicich.danmsmateriales.repository.IProductJpaRepository;
import com.durandsuppicich.danmsmateriales.domain.OrderItem;
import com.durandsuppicich.danmsmateriales.domain.Product;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    private final IProductJpaRepository productRepository;
    private final IStockMovementService stockMovementService;
    private final IOrderItemJpaRepository orderItemRepository;
    private final IProvisionService provisionService;
    private final MeterRegistry meterRegistry;
    private Counter postCounter;

    public ProductService(
            IProductJpaRepository productRepository,
            IStockMovementService stockMovementService,
            IOrderItemJpaRepository orderItemRepository,
            IProvisionService provisionService,
            MeterRegistry meterRegistry) {

        this.productRepository = productRepository;
        this.stockMovementService = stockMovementService;
        this.orderItemRepository = orderItemRepository;
        this.provisionService = provisionService;
        this.meterRegistry = meterRegistry;
        initCounter();
    }

    private void initCounter() {
        postCounter = meterRegistry.counter("orders.posts");
    }

    @Override
    public Product post(Product product) {
        postCounter.increment();
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Integer id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductIdNotFoundException(id));
    }

    @Override
    public Product getByName(String name) {
        return productRepository
                .findByName(name)
                .orElseThrow(() -> new NameNotFoundException(name));
    }

    @Override
    public List<Product> getByStockRange(Integer minimumStock, Integer maximumStock) {
        return productRepository.findByCurrentStockBetween(minimumStock, maximumStock);
    }

    @Override
    public List<Product> getByPriceRange(Double minimumPrice, Double maximumPrice) {
        return productRepository.findByPriceBetween(minimumPrice, maximumPrice);
    }

    @Override
    public void put(Product product, Integer id) {

        productRepository
                .findById(id)
                .map(p -> {
                    p.setId(product.getId());
                    p.setName(product.getName());
                    p.setDescription(product.getDescription());
                    p.setPrice(product.getPrice());
                    p.setMinimumStock(product.getMinimumStock());
                    p.setUnit(product.getUnit());
                    return productRepository.save(p);
                })
                .orElseThrow(() -> new ProductIdNotFoundException(id));
    }

    @Override
    public void delete(Integer id) {

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        else {
            throw new ProductIdNotFoundException(id);
        }
    }

    @JmsListener(destination = "COLA_PEDIDOS")
    public void handle(ObjectMessage message) {

        try {
            Integer orderId = (Integer) message.getObject();

            List<OrderItem> items = orderItemRepository.findAllByOrderId(orderId);

            stockMovementService.generateStockMovement(items);

            updateStock(items);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void updateStock(List<OrderItem> items) {

        List<Product> products = new ArrayList<>();

        for(OrderItem oi: items) {

            Product product = oi.getProduct();

            Integer newStock = product.getCurrentStock() - oi.getQuantity();

            product.setCurrentStock(newStock);

            if (newStock <= product.getMinimumStock()) {
                products.add(product);
            }

            productRepository.save(product);
        }

        if (!products.isEmpty()) {
            provisionService.generateProvisionOrder(products);
        }
    }
}
