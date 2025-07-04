package ru.alfabank.practice.kagrishin.bankonboarding.model.dto.dadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Suggestion(

        String value,
        String unrestrictedValue,
        Data data
) {
}