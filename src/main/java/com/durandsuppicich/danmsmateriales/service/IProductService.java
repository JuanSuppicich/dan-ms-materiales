package com.durandsuppicich.danmsmateriales.service;

import java.util.List;

import com.durandsuppicich.danmsmateriales.domain.Product;

public interface IProductService {

    Product post(Product product);

    List<Product> getAll();

    Product getById(Integer id);

    Product getByName(String name);

    List<Product> getByStockRange(Integer minimumStock, Integer maximumStock);

    List<Product> getByPriceRange(Double minimumPrice, Double maximumPrice);

    void put(Product product, Integer id);

    void delete(Integer id);
}
