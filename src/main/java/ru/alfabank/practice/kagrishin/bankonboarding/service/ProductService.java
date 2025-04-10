package ru.alfabank.practice.kagrishin.bankonboarding.service;

import org.springframework.stereotype.Component;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.entity.ProductEntity;
import ru.alfabank.practice.kagrishin.bankonboarding.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findByIsAvailableTrue().stream().map(productEntity -> new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getIsAvailable())
        ).toList();
    }

    public Optional<Product> getProduct(String id) {
        return productRepository.findById(id).map(
                productEntity -> (new Product(
                        productEntity.getId(),
                        productEntity.getName(),
                        productEntity.getPrice(),
                        productEntity.getIsAvailable()
                )
        ));
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
