package ru.alfabank.practice.kagrishin.bankonboarding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductSummary {

    private BigDecimal sum;
    private List<Product> products;
}
