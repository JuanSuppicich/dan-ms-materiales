package com.durandsuppicich.danmsmateriales.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.durandsuppicich.danmsmateriales.repository.IProvisionJpaRepository;
import com.durandsuppicich.danmsmateriales.domain.ProvisionItem;
import com.durandsuppicich.danmsmateriales.domain.Product;
import com.durandsuppicich.danmsmateriales.domain.Provision;

import org.springframework.stereotype.Service;

@Service
public class ProvisionService implements IProvisionService {

    private final IProvisionJpaRepository provisionRepository;

    public ProvisionService(IProvisionJpaRepository provisionRepository) {
        this.provisionRepository = provisionRepository;
    }

    @Override
    public void generateProvisionOrder(List<Product> products) {
        // TODO No deberia generar una orden de provision si ya hay una orden pendiente para ese producto..
        Provision provision = new Provision();

        provision.setProvisionDate(
                Instant.now().truncatedTo(ChronoUnit.DAYS).plus(7L, ChronoUnit.DAYS));
        
        for (Product product: products) {

            ProvisionItem provisionItem = new ProvisionItem();

            provisionItem.setProduct(product);
            // TODO provision quantity not defined
            provisionItem.setQuantity(4 * product.getMinimumStock());

            provision.addProvisionItem(provisionItem);
        }

        provisionRepository.save(provision);
    }   
}
