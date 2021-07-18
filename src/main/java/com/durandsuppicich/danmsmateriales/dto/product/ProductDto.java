package com.durandsuppicich.danmsmateriales.dto.product;

public class ProductDto {

    private Integer id;

    private String name;

    private String description;

    private Integer currentStock;

    private String unitDescription;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }
}
