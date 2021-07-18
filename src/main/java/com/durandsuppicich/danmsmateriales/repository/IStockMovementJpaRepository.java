package com.durandsuppicich.danmsmateriales.repository;

import com.durandsuppicich.danmsmateriales.domain.StockMovement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockMovementJpaRepository extends JpaRepository<StockMovement, Integer> { }