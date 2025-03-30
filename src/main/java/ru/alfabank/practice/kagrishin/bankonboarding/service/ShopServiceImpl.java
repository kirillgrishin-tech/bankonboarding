package ru.alfabank.practice.kagrishin.bankonboarding.service;

import org.springframework.stereotype.Service;
import ru.alfabank.practice.kagrishin.bankonboarding.exception.ProductNotFoundException;
import ru.alfabank.practice.kagrishin.bankonboarding.mapper.service.ProductServiceMapper;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.ProductSummary;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDto;
import ru.alfabank.practice.kagrishin.bankonboarding.gateway.ProductGateway;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    private final ProductGateway productGateway;
    private final ProductServiceMapper productServiceMapper;

    public ShopServiceImpl(ProductGateway productGateway, ProductServiceMapper productServiceMapper) {
        this.productGateway = productGateway;
        this.productServiceMapper = productServiceMapper;
    }

    @Override
    public String getWelcomeMessage() {
        return "Добро пожаловать в наш чудесный магазин";
    }

    @Override
    public List<Product> getAllProducts() {
        return productServiceMapper.productDtoListToProductList(
                productGateway.getAvailableProducts()
        );
    }

    @Override
    public ProductSummary calculateProducts(List<Product> products) {
        if (Objects.isNull(products)) {
            return new ProductSummary();
        }
        List<Product> matchedProducts = findAndAggregateMatchedProductsFromStorage(products);
        BigDecimal sum = calculateSumOfAllProducts(matchedProducts);
        return new ProductSummary(sum, matchedProducts);
    }

    private List<Product> findAndAggregateMatchedProductsFromStorage(List<Product> products) {
        return products.stream().map(
                product ->
                        Optional.ofNullable(productGateway.getProduct(product.getId()))
                                .filter(ProductDto::isAvailable).map(
                                        productDto -> new Product(
                                                productDto.id(),
                                                productDto.name(),
                                                productDto.price(),
                                                product.getQuantity()))
                                .orElseThrow(() -> new ProductNotFoundException(product))
        ).toList();
    }

    private BigDecimal calculateSumOfAllProducts(List<Product> products) {
        return products.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
