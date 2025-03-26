package ru.alfabank.practice.kagrishin.bankonboarding.service;

import org.springframework.stereotype.Service;
import ru.alfabank.practice.kagrishin.bankonboarding.exception.ProductNotFoundException;
import ru.alfabank.practice.kagrishin.bankonboarding.mapper.service.ProductServiceMapper;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.ProductSummary;
import ru.alfabank.practice.kagrishin.bankonboarding.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    private final ProductRepository productRepository;

    public ShopServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return ProductServiceMapper.productDtoListToProductList(productRepository.getAllProducts());
    }

    @Override
    public ProductSummary calculateProducts(List<Product> products) {
        if (Objects.isNull(products)) {
            return null;
        }
        ProductSummary productSummary = new ProductSummary(new ArrayList<>());
        BigDecimal sum = products.stream().map(product ->
            Optional.ofNullable(productRepository.getProduct(product.getId())).map(
                    productDto -> {
                        productSummary.getProducts()
                                .add(new Product(
                                        productDto.id(),
                                        productDto.name(),
                                        productDto.price(),
                                        product.getQuantity())
                                );
                        Integer productQuantity = product.getQuantity();
                        return productDto.price().multiply(BigDecimal.valueOf(productQuantity));
                    }
            ).orElseThrow(()-> new ProductNotFoundException(product))
        ).reduce(BigDecimal.ZERO, BigDecimal::add);
        productSummary.setSum(sum);
        return productSummary;
    }
}
