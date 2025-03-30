package ru.alfabank.practice.kagrishin.bankonboarding.storage;

import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductStorage {

    List<ProductDto> getAllProducts();
    List<ProductDto> getAvailableProducts();
    Optional<ProductDto> getProduct(String id);
    void save(ProductDto product);
}
