package ru.alfabank.practice.kagrishin.bankonboarding.repository;

import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDto;

import java.util.Map;

public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Integer,ProductDto> productMap;

    public ProductRepositoryImpl(Map<Integer,ProductDto> productMap) {
        this.productMap = productMap;
    }

    @Override
    public Map<Integer,ProductDto> getAllProducts() {
        return productMap;
    }

    @Override
    public ProductDto getProduct(Integer id) {
        return productMap.get(id);
    }
}
