package com.durandsuppicich.danmsmateriales.dao;

import java.util.List;

import com.durandsuppicich.danmsmateriales.domain.Material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialJpaRepository extends JpaRepository<Material, Integer> {

    List<Material> findByNombreContaining(String nombre);
}