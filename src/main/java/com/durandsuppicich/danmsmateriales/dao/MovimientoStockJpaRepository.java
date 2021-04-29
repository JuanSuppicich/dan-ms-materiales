package com.durandsuppicich.danmsmateriales.dao;

import com.durandsuppicich.danmsmateriales.domain.MovimientoStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoStockJpaRepository extends JpaRepository<MovimientoStock, Integer> { }