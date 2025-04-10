package ru.alfabank.practice.kagrishin.bankonboarding.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductSummaryDto(@NotBlank(message="id required") String id, @PositiveOrZero(message="quantity required") Integer quantity) {}
