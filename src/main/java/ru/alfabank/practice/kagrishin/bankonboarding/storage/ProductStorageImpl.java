package ru.alfabank.practice.kagrishin.bankonboarding.storage;

import org.springframework.stereotype.Component;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDocument;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDto;
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
        return productRepository.findByIsAvailableTrue().stream().map(product -> new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.isAvailable())
        ).toList();
    }

    @Override
    public Optional<ProductDto> getProduct(String id) {
        return productRepository.findById(id).map(
                product -> (new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.isAvailable()
                )
        ));
    }

    @Override
    public void save(ProductDto product) {
        productRepository.save(new ProductDocument(product.id(), product.name(), product.price(), product.isAvailable()));
    }
}
