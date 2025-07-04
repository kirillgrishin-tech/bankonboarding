package ru.alfabank.practice.kagrishin.bankonboarding.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponse {

    private final String message;
}
