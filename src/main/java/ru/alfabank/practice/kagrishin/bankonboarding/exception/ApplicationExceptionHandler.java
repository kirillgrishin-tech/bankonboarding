package ru.alfabank.practice.kagrishin.bankonboarding.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ErrorResponse;
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

    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<String> handleException(FeignException ex) {
        return ResponseEntity
                .status(ex.status())
                .body(ex.contentUTF8());
    }

    @ExceptionHandler(AddressNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleException(AddressNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
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
