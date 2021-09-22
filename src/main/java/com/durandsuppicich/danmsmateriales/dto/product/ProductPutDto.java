package com.durandsuppicich.danmsmateriales.dto.product;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class ProductPutDto {

    @NotNull
    @Positive
    private Integer id;

    @NotBlank
    @Size(max = 32)
    private String name;

    @Size(max = 64)
    private String description;

    @Positive
    private Double price;

    @NotNull
    @PositiveOrZero
    private Integer minimumStock;

    @NotNull
    @Positive
    private Integer unitId;

    @Null // not fully supported yet
    @Range(min = 0, max = 500)
    @Digits(integer = 3, fraction = 3)
    private Double weight;

    @Null // not fully supported yet
    @Range(min = 0, max = 2)
    @Digits(integer = 1, fraction = 3)
    private Double volume;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getVolume() {
        return volume;
    }
}
