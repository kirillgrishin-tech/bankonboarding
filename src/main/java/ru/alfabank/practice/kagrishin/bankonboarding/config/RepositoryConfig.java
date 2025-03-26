package ru.alfabank.practice.kagrishin.bankonboarding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDto;
import ru.alfabank.practice.kagrishin.bankonboarding.repository.ProductRepository;
import ru.alfabank.practice.kagrishin.bankonboarding.repository.ProductRepositoryImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RepositoryConfig {

    @Bean
    public ProductRepository createProductRepository() {
        Map<Integer, ProductDto> productList = new HashMap<>();
        productList.put(1, new ProductDto(1, "milk", BigDecimal.valueOf(127.23)));
        productList.put(2, new ProductDto(2, "bread", BigDecimal.valueOf(122.23)));
        productList.put(3, new ProductDto(3, "cake", BigDecimal.valueOf(65.23)));
        return new ProductRepositoryImpl(productList);
    }
}
