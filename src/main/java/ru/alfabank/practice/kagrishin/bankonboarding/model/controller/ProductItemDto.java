package ru.alfabank.practice.kagrishin.bankonboarding.model.controller;

import java.math.BigDecimal;

public record ProductItemDto(Integer id, String name, BigDecimal price, Integer quantity) {}
