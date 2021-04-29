package com.durandsuppicich.danmsmateriales.dao;

import com.durandsuppicich.danmsmateriales.domain.Provision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvisionJpaRepository extends JpaRepository<Provision, Integer> { }