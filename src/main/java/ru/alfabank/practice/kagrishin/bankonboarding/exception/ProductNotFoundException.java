package ru.alfabank.practice.kagrishin.bankonboarding.exception;

import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;

public class ProductNotFoundException extends RuntimeException {

    private final Product product;

    public Product getProduct() {
        return product;
    }

    public ProductNotFoundException(Product product) {
        this.product = product;
    }
}
