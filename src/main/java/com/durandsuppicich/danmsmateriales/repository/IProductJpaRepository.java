package com.durandsuppicich.danmsmateriales.repository;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsmateriales.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductJpaRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String name);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.id IN :ids")
    List<Product> findAllByIds(List<Integer> ids);

    List<Product> findByCurrentStockBetween(Integer minimumStock, Integer maximumStock);

    List<Product> findByPriceBetween(Double minimumPrice, Double maximumPrice);
}
