package ru.alfabank.practice.kagrishin.bankonboarding.model.controller;

import java.math.BigDecimal;
import java.util.List;

public record ProductSummaryResponseDto(BigDecimal sum, List<ProductItemDto> products) {}
