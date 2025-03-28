package ru.alfabank.practice.kagrishin.bankonboarding.repository;

import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDto;

import java.util.List;

public interface ProductStorage {

    List<ProductDto> getAllProducts();
    List<ProductDto> getAvailableProducts();
    ProductDto getProduct(String id);
    void save(ProductDto product);
}
