package com.durandsuppicich.danmsmateriales.repository;

import com.durandsuppicich.danmsmateriales.domain.ProvisionItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvisionItemJpaRepository extends JpaRepository<ProvisionItem, Integer> { }