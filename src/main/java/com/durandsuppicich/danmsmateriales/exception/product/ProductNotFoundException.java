package com.durandsuppicich.danmsmateriales.exception.product;

import com.durandsuppicich.danmsmateriales.exception.http.NotFoundException;

public class ProductNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "Product could not be found.";

    public ProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductNotFoundException(String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
