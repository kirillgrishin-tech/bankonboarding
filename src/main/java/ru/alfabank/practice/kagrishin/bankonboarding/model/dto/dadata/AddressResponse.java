package ru.alfabank.practice.kagrishin.bankonboarding.model.dto.dadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressResponse(List<Suggestion> suggestions) {
}