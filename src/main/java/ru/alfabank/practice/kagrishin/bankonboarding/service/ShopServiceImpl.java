package ru.alfabank.practice.kagrishin.bankonboarding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alfabank.practice.kagrishin.bankonboarding.config.BusinessConfig;
import ru.alfabank.practice.kagrishin.bankonboarding.exception.ProductNotFoundException;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Discount;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.ProductSummary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ProductService productService;
    private final DiscountService discountService;
    private final BusinessConfig businessConfig;

    @Override
    public String getWelcomeMessage() {
        return "Добро пожаловать в наш чудесный магазин";
    }

    @Override
    public List<Product> getAllProducts() {
        return productService.getAvailableProducts();
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
                        productService.getProduct(product.getId())
                                .filter(Product::getIsAvailable).map(
                                        foundProduct -> Product.builder()
                                                .id(foundProduct.getId())
                                                .name(foundProduct.getName())
                                                .price(foundProduct.getPrice())
                                                .quantity(product.getQuantity())
                                                .build()
                                )
                                .orElseThrow(() -> new ProductNotFoundException(product))
        ).toList();
    }

    private BigDecimal calculateSumOfAllProducts(List<Product> products) {
        return products.stream()
                .map(product -> {
                    int discount = discountService.getDiscounts(product.getId())
                            .stream()
                            .map(Discount::getPercent)
                            .reduce(0,Integer::sum);
                    if (businessConfig.getMaxDiscount() > 0 && discount > businessConfig.getMaxDiscount()) {
                        discount = businessConfig.getMaxDiscount();
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
