package com.durandsuppicich.danmsmateriales.dao;

import com.durandsuppicich.danmsmateriales.domain.Unidad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadJpaRepository extends JpaRepository<Unidad, Integer> { }