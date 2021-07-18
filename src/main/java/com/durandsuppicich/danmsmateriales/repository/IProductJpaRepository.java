package com.durandsuppicich.danmsmateriales.repository;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsmateriales.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductJpaRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String name);

    List<Product> findByCurrentStockBetween(Integer minimumStock, Integer maximumStock);

    List<Product> findByPriceBetween(Double minimumPrice, Double maximumPrice);
}
