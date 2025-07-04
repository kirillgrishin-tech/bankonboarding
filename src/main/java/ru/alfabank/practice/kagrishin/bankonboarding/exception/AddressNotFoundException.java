package ru.alfabank.practice.kagrishin.bankonboarding.exception;

import lombok.Getter;

@Getter
public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(String message) {
        super(message);
    }
}
