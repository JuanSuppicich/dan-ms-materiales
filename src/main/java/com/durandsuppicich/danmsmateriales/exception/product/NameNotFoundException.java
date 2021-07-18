package com.durandsuppicich.danmsmateriales.exception.product;

public class NameNotFoundException extends ProductNotFoundException {

    private static final String DEFAULT_MESSAGE = "The given name does not belong to any product.";

    public NameNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public NameNotFoundException(String name) {
        super(DEFAULT_MESSAGE + " {" + name + "}");
    }
}
