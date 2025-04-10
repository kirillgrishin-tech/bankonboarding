package ru.alfabank.practice.kagrishin.bankonboarding.exception;

import lombok.Getter;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;

@Getter
public class ProductNotFoundException extends RuntimeException {

    private final Product product;

    public ProductNotFoundException(Product product) {
        this.product = product;
    }
}
