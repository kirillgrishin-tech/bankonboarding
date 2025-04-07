package ru.alfabank.practice.kagrishin.bankonboarding.storage;

import org.springframework.stereotype.Component;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductEntity;
import ru.alfabank.practice.kagrishin.bankonboarding.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ProductStorageImpl implements ProductStorage {

    private final ProductRepository productRepository;

    public ProductStorageImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAvailableProducts() {
        return productRepository.findByIsAvailableTrue().stream().map(productEntity -> new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.isAvailable())
        ).toList();
    }

    @Override
    public Optional<Product> getProduct(String id) {
        return productRepository.findById(id).map(
                productEntity -> (new Product(
                        productEntity.getId(),
                        productEntity.getName(),
                        productEntity.getPrice(),
                        productEntity.isAvailable()
                )
        ));
    }

    @Override
    public void save(Product product) {
        productRepository.save(new ProductEntity(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.isAvailable()
        ));
    }
}
