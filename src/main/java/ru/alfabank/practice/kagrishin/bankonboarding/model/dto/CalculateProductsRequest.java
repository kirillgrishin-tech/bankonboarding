package ru.alfabank.practice.kagrishin.bankonboarding.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CalculateProductsRequest(
        @NotBlank(message = "deliveryAddress required") String deliveryAddress,
        @NotNull List<ProductSummaryDto> products) {
}
