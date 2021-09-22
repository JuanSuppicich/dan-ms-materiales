package com.durandsuppicich.danmsmateriales.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.durandsuppicich.danmsmateriales.repository.IStockMovementJpaRepository;
import com.durandsuppicich.danmsmateriales.domain.OrderItem;
import com.durandsuppicich.danmsmateriales.domain.StockMovement;

import org.springframework.stereotype.Service;

@Service
public class StockMovementService implements IStockMovementService {

    private final IStockMovementJpaRepository stockMovementRepository;

    public StockMovementService(IStockMovementJpaRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    @Override
    public void generateStockMovement(List<OrderItem> items) {
        
        for (OrderItem item: items) {

            StockMovement stockMovement = new StockMovement();

            stockMovement.setOrderItem(item);
            stockMovement.setProduct(item.getProduct());
            stockMovement.setQuantity(item.getQuantity());
            stockMovement.setMovementDate(
                    Instant.now().truncatedTo(ChronoUnit.MILLIS));
            
            stockMovementRepository.save(stockMovement);
        }
    }
}
