package ru.alfabank.practice.kagrishin.bankonboarding.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "ProductSummary{" +
                "sum=" + sum +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductSummary that)) return false;
        return Objects.equals(sum, that.sum) && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum, products);
    }
}
