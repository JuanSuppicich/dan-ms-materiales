package com.durandsuppicich.danmsmateriales.repository;

import com.durandsuppicich.danmsmateriales.domain.Unit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUnitJpaRepository extends JpaRepository<Unit, Integer> { }