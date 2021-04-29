package com.durandsuppicich.danmsmateriales.dao;

import com.durandsuppicich.danmsmateriales.domain.DetalleProvision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleProvisionJpaRepository extends JpaRepository<DetalleProvision, Integer> { }