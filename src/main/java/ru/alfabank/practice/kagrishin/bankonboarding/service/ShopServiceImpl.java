package ru.alfabank.practice.kagrishin.bankonboarding.service;

import org.springframework.stereotype.Service;
import ru.alfabank.practice.kagrishin.bankonboarding.exception.ProductNotFoundException;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Discount;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.ProductSummary;
import ru.alfabank.practice.kagrishin.bankonboarding.storage.BusinessSettingStorage;
import ru.alfabank.practice.kagrishin.bankonboarding.storage.DiscountStorage;
import ru.alfabank.practice.kagrishin.bankonboarding.storage.ProductStorage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Service
public class ShopServiceImpl implements ShopService {

    private final ProductStorage productStorage;
    private final DiscountStorage discountStorage;
    private final BusinessSettingStorage businessSettingStorage;

    public ShopServiceImpl(ProductStorage productStorage, DiscountStorage discountStorage, BusinessSettingStorage businessSettingStorage) {
        this.productStorage = productStorage;
        this.discountStorage = discountStorage;
        this.businessSettingStorage = businessSettingStorage;
    }

    @Override
    public String getWelcomeMessage() {
        return "Добро пожаловать в наш чудесный магазин";
    }

    @Override
    public List<Product> getAllProducts() {
        return productStorage.getAvailableProducts();
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
                        productStorage.getProduct(product.getId())
                                .filter(Product::isAvailable).map(
                                        foundProduct -> new Product(
                                                foundProduct.getId(),
                                                foundProduct.getName(),
                                                foundProduct.getPrice(),
                                                product.getQuantity()))
                                .orElseThrow(() -> new ProductNotFoundException(product))
        ).toList();
    }

    private BigDecimal calculateSumOfAllProducts(List<Product> products) {
        return products.stream()
                .map(product -> {
                    int discount = discountStorage.getDiscounts(product.getId())
                            .stream()
                            .map(Discount::getPercent)
                            .reduce(0,Integer::sum);
                    if (businessSettingStorage.getMaxDiscount() > 0 && discount > businessSettingStorage.getMaxDiscount()) {
                        discount = businessSettingStorage.getMaxDiscount();
                    }
                    BigDecimal priceWithDiscount = calculateProductPriceWithDiscount(product.getPrice(), discount);
                    return priceWithDiscount.multiply(BigDecimal.valueOf(product.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateProductPriceWithDiscount(BigDecimal price, int discount) {
        if (Objects.isNull(price) || discount <= 0) {
            return price;
        }
        return price.multiply(BigDecimal.valueOf(100 - discount))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
}
