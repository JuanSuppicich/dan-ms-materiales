package com.durandsuppicich.danmsmateriales.mapper;

import com.durandsuppicich.danmsmateriales.domain.Product;
import com.durandsuppicich.danmsmateriales.domain.Unit;
import com.durandsuppicich.danmsmateriales.dto.product.ProductDto;
import com.durandsuppicich.danmsmateriales.dto.product.ProductPostDto;
import com.durandsuppicich.danmsmateriales.dto.product.ProductPutDto;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper implements IProductMapper {

    @Override
    public Product map(ProductPostDto productDto) {
        Product product = new Product();

        Unit unit = new Unit();
        unit.setId(productDto.getUnitId());

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCurrentStock(productDto.getCurrentStock());
        product.setMinimumStock(productDto.getMinimumStock());
        product.setUnit(unit);

        return product;
    }

    @Override
    public Product map(ProductPutDto productDto) {
        Product product = new Product();

        Unit unit = new Unit();
        unit.setId(productDto.getUnitId());

        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setMinimumStock(productDto.getMinimumStock());
        product.setUnit(unit);

        return product;
    }

    @Override
    public ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCurrentStock(product.getCurrentStock());
        productDto.setMinimumStock(product.getMinimumStock());
        productDto.setPrice(product.getPrice());
        productDto.setUnitDescription(product.getUnit().getDescription());
        productDto.setUnitId(product.getUnit().getId());

        return productDto;
    }

    @Override
    public List<ProductDto> mapToDto(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            productDtos.add(mapToDto(product));
        }

        return productDtos;
    }
}
