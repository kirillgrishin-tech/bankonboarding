package ru.alfabank.practice.kagrishin.bankonboarding.mapper.service;

import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProductServiceMapper {

    private ProductServiceMapper() {
        throw new UnsupportedOperationException();
    }

    public static List<Product> productDtoListToProductList(Map<Integer, ProductDto> productDtoMap) {
        if (Objects.isNull(productDtoMap)) {
            return new ArrayList<>();
        }
        List<Product> products = new ArrayList<>();
        productDtoMap.forEach(
                (key, value) -> products.add(new Product(value.id(), value.name(), value.price()))
        );
        return products;
    }
}
