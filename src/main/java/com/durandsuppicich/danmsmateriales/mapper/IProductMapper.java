package com.durandsuppicich.danmsmateriales.mapper;

import com.durandsuppicich.danmsmateriales.domain.Product;
import com.durandsuppicich.danmsmateriales.dto.product.ProductDto;
import com.durandsuppicich.danmsmateriales.dto.product.ProductPostDto;
import com.durandsuppicich.danmsmateriales.dto.product.ProductPutDto;

import java.util.List;

public interface IProductMapper {

    Product map(ProductPostDto productDto);

    Product map(ProductPutDto productDto);

    ProductDto mapToDto(Product product);

    List<ProductDto> mapToDto(List<Product> products);
}
