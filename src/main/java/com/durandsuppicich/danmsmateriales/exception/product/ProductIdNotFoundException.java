package com.durandsuppicich.danmsmateriales.exception.product;

public class ProductIdNotFoundException extends ProductNotFoundException {

    private static final String DEFAULT_MESSAGE = "The given id does not belong to any product.";

    public ProductIdNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductIdNotFoundException(Integer id) {
        super(DEFAULT_MESSAGE + " {" + id + "}");
    }
}
