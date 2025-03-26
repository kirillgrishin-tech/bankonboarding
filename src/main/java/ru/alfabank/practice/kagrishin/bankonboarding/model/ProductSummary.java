package ru.alfabank.practice.kagrishin.bankonboarding.model;

import java.math.BigDecimal;
import java.util.List;

public class ProductSummary {

    private BigDecimal sum = BigDecimal.ZERO;
    private List<Product> products;

    public ProductSummary(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
