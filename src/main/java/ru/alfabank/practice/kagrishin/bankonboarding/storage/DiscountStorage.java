package ru.alfabank.practice.kagrishin.bankonboarding.storage;

import ru.alfabank.practice.kagrishin.bankonboarding.model.Discount;

import java.util.List;

public interface DiscountStorage {

    List<Discount> getDiscounts(String productId);
}
