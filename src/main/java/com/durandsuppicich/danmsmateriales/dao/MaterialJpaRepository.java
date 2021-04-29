package com.durandsuppicich.danmsmateriales.dao;

import com.durandsuppicich.danmsmateriales.domain.Material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialJpaRepository extends JpaRepository<Material, Integer> { }