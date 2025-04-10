package ru.alfabank.practice.kagrishin.bankonboarding.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @EqualsAndHashCode.Include
    private final String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Boolean isAvailable;
}
