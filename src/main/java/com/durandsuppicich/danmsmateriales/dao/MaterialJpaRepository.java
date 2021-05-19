package com.durandsuppicich.danmsmateriales.dao;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsmateriales.domain.Material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialJpaRepository extends JpaRepository<Material, Integer> {

    Optional<Material> findByNombre(String nombre);

    List<Material> findByStockActualBetween(Integer stockMinimo, Integer stockMaximo);

    List<Material> findByPrecioBetween(Double precioMinimo, Double precioMaximo);
}