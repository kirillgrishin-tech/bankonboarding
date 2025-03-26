package ru.alfabank.practice.kagrishin.bankonboarding.model.repository;

import java.math.BigDecimal;

public record ProductDto(Integer id, String name, BigDecimal price) {}
