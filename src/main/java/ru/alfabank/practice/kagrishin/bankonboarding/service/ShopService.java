package ru.alfabank.practice.kagrishin.bankonboarding.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.alfabank.practice.kagrishin.bankonboarding.config.BusinessConfig;
import ru.alfabank.practice.kagrishin.bankonboarding.exception.AddressNotFoundException;
import ru.alfabank.practice.kagrishin.bankonboarding.exception.ProductNotFoundException;
import ru.alfabank.practice.kagrishin.bankonboarding.logging.Log;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Discount;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.ProductSummary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ProductService productService;
    private final DadataService dadataService;
    private final DiscountService discountService;
    private final BusinessConfig businessConfig;

    @Log
    public String getWelcomeMessage() {
        return "Добро пожаловать в наш чудесный магазин";
    }

    @Log
    public List<Product> getAllProducts() {
        return productService.getAvailableProducts();
    }

    @Log
    public ProductSummary calculateProducts(@NotNull List<Product> products, @NotNull String deliveryAddress) {
        dadataService.getAddresses(deliveryAddress).stream()
                .filter(address -> StringUtils.equals(address.getFiasLevel(), "9"))
                .findFirst()
                .orElseThrow(() -> new AddressNotFoundException("Address: " + deliveryAddress + " not found!"));
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

    private BigDecimal calculateSumOfAllProducts(@NotNull List<Product> products) {
        return products.stream()
                .map(product -> {
                    int discount = discountService.getDiscounts(product.getId())
                            .orElse(new ArrayList<>())
                            .stream()
                            .map(Discount::getPercent)
                            .reduce(0, Integer::sum);
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
