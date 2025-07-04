package ru.alfabank.practice.kagrishin.bankonboarding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alfabank.practice.kagrishin.bankonboarding.entity.ProductEntity;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAvailableProducts() {
        return productRepository.findByIsAvailableTrue().stream()
                .map(productEntity -> Product.builder()
                        .id(productEntity.getId())
                        .name(productEntity.getName())
                        .price(productEntity.getPrice())
                        .isAvailable(productEntity.getIsAvailable())
                        .build()
                ).toList();
    }

    public List<Product> getUnavailableProducts() {
        return productRepository.findByIsAvailableFalse().stream()
                .map(productEntity -> Product.builder()
                        .id(productEntity.getId())
                        .name(productEntity.getName())
                        .price(productEntity.getPrice())
                        .isAvailable(productEntity.getIsAvailable())
                        .build()
                ).toList();
    }

    public Optional<Product> getProduct(String id) {
        return productRepository.findById(id).map(
                productEntity ->
                        Product.builder()
                                .id(productEntity.getId())
                                .name(productEntity.getName())
                                .price(productEntity.getPrice())
                                .isAvailable(productEntity.getIsAvailable())
                                .build()
        );
    }

    public void save(Product product) {
        productRepository.save(new ProductEntity(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getIsAvailable()
        ));
    }
}
