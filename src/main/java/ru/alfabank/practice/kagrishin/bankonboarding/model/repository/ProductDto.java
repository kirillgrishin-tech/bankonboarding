package ru.alfabank.practice.kagrishin.bankonboarding.model.repository;

import java.math.BigDecimal;

public record ProductDto(String id, String name, BigDecimal price, Boolean isAvailable) {}
