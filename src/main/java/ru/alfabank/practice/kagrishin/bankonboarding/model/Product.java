package ru.alfabank.practice.kagrishin.bankonboarding.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @EqualsAndHashCode.Include
    private final String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Boolean isAvailable;

    public Product(String id, String name, BigDecimal price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String id, String name, BigDecimal price, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Product(String id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
