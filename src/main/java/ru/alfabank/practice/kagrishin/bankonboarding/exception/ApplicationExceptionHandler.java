package ru.alfabank.practice.kagrishin.bankonboarding.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.ParameterErrors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ProductSummaryDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    protected ResponseEntity<ProductSummaryDto> handleException(ProductNotFoundException ex) {
        ProductSummaryDto productSummaryDto = Optional.ofNullable(ex.getProduct())
                .map(product -> new ProductSummaryDto(product.getId(), product.getQuantity()))
                .orElse(null);
        return new ResponseEntity<>(productSummaryDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    protected ResponseEntity<Map<String, String>> handleValidationExceptions(HandlerMethodValidationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getParameterValidationResults().forEach(error -> {
            if (error instanceof ParameterErrors parameterErrors) {
                parameterErrors.getFieldErrors().forEach( parameterError ->
                        errors.put(parameterError.getField(), parameterError.getDefaultMessage())
                );
            }
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
