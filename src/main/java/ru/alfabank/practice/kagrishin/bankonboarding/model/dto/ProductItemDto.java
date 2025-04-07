package ru.alfabank.practice.kagrishin.bankonboarding.model.dto;

import java.math.BigDecimal;

public record ProductItemDto(String id, String name, BigDecimal price, int quantity) {}
