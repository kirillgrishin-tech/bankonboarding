package ru.alfabank.practice.kagrishin.bankonboarding.repository;

import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDto;

import java.util.Map;

public interface ProductRepository {

    Map<Integer,ProductDto> getAllProducts();
    ProductDto getProduct(Integer id);
}
