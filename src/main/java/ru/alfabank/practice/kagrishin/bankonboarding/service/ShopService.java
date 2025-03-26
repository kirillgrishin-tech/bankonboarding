package ru.alfabank.practice.kagrishin.bankonboarding.service;


import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.ProductSummary;

import java.util.List;

public interface ShopService {

    default String getWelcomeMessage() {
        return "Добро пожаловать в наш чудесный магазин";
    }

    List<Product> getAllProducts();
    ProductSummary calculateProducts(List<Product> products);
}
