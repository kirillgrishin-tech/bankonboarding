package ru.alfabank.practice.kagrishin.bankonboarding.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.alfabank.practice.kagrishin.bankonboarding.exception.ProductNotFoundException;
import ru.alfabank.practice.kagrishin.bankonboarding.model.controller.ProductSummaryDto;

import java.util.Optional;

@ControllerAdvice
public class ShopControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    protected ResponseEntity<ProductSummaryDto> handleException(ProductNotFoundException ex) {
        ProductSummaryDto productSummaryDto = Optional.ofNullable(ex.getProduct())
                .map(product -> new ProductSummaryDto(product.getId(), product.getQuantity()))
                .orElse(null);
        return new ResponseEntity<>(productSummaryDto, HttpStatus.BAD_REQUEST);
    }
}
