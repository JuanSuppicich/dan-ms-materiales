package com.durandsuppicich.danmsmateriales.dto.product;

import javax.validation.constraints.*;

public class ProductPostDto {

    @NotBlank
    @Size(max = 32)
    private String name;

    @Size(max = 64)
    private String description;

    @Positive
    private Double price;

    @NotNull
    @PositiveOrZero
    private Integer currentStock;

    @NotNull
    @PositiveOrZero
    private Integer minimumStock;

    @NotNull
    @Positive
    private Integer unitId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public Integer getUnitId() {
        return unitId;
    }
}