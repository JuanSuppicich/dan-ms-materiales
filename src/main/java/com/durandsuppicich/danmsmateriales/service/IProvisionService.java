package com.durandsuppicich.danmsmateriales.service;

import java.util.List;

import com.durandsuppicich.danmsmateriales.domain.Product;

public interface IProvisionService {

    void generateProvisionOrder(List<Product> products);
}
