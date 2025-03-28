package ru.alfabank.practice.kagrishin.bankonboarding.repository;

import org.springframework.stereotype.Component;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDocument;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDto;

import java.util.List;

@Component
public class MongoDbProductStorage implements ProductStorage {

    private final ProductRepository productRepository;

    public MongoDbProductStorage(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map( product -> new ProductDto(
                                product.getId(),
                                product.getName(),
                                product.getPrice(),
                                product.isAvailable())
        ).toList();
    }

    @Override
    public List<ProductDto> getAvailableProducts() {
        return productRepository.getAvailableProducts().stream().map( product -> new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.isAvailable())
        ).toList();
    }

    @Override
    public ProductDto getProduct(String id) {
        return productRepository.findById(id).map(
                product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.isAvailable()
                )
        ).orElse(null);
    }

    @Override
    public void save(ProductDto product) {
        productRepository.save(new ProductDocument(product.id(), product.name(), product.price(), product.isAvailable()));
    }
}
