package ru.alfabank.practice.kagrishin.bankonboarding.model.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductSummaryResponse(BigDecimal sum, List<ProductItemDto> products) {}
