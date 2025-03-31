package ru.alfabank.practice.kagrishin.bankonboarding.storage;

import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductStorage {

    List<Product> getAllProducts();
    List<Product> getAvailableProducts();
    Optional<Product> getProduct(String id);
    void save(Product product);
}
