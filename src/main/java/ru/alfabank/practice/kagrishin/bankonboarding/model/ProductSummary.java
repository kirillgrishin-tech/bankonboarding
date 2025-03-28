package ru.alfabank.practice.kagrishin.bankonboarding.model;

import java.math.BigDecimal;
import java.util.List;

public class ProductSummary {

    private BigDecimal sum;
    private List<Product> products;

    public ProductSummary(BigDecimal sum,List<Product> products) {
        this.sum = sum;
        this.products = products;
    }

    public ProductSummary() {
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
