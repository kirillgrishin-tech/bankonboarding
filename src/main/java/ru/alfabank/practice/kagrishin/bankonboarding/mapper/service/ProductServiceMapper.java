package ru.alfabank.practice.kagrishin.bankonboarding.mapper.service;

import org.springframework.stereotype.Component;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProductServiceMapper {

    public List<Product> productDtoListToProductList(List<ProductDto> productDtoList) {
        if (Objects.isNull(productDtoList)) {
            return new ArrayList<>();
        }
        return productDtoList.stream().map(
                productDto -> new Product(productDto.id(), productDto.name(), productDto.price())
        ).toList();
    }
}
