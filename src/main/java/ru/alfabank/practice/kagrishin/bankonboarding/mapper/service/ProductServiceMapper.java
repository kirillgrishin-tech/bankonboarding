package ru.alfabank.practice.kagrishin.bankonboarding.mapper.service;

import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductServiceMapper {

    private ProductServiceMapper() {
        throw new UnsupportedOperationException();
    }

    public static List<Product> productDtoListToProductList(List<ProductDto> productDtoList) {
        if (Objects.isNull(productDtoList)) {
            return new ArrayList<>();
        }
        return productDtoList.stream().map(
                productDto -> new Product(productDto.id(), productDto.name(), productDto.price())
        ).toList();
    }
}
