package ru.alfabank.practice.kagrishin.bankonboarding.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alfabank.practice.kagrishin.bankonboarding.mapper.ProductProductDtoMapper;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.ProductSummary;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.CalculateProductsRequest;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.CalculateProductsResponse;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ProductDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.WelcomeResponse;
import ru.alfabank.practice.kagrishin.bankonboarding.service.ShopService;

import java.util.List;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ProductProductDtoMapper productProductDtoMapper;
    private final ShopService shopService;

    @GetMapping("/welcome")
    public WelcomeResponse welcome() {
        String message = shopService.getWelcomeMessage();
        return new WelcomeResponse(message);
    }

    @GetMapping("/product")
    public List<ProductDto> getAllProducts() {
        return productProductDtoMapper.productListToProductDtoList(shopService.getAllProducts());
    }

    @PostMapping("/calc")
    public CalculateProductsResponse calculateProducts(
            @Valid @RequestBody CalculateProductsRequest calculateProductsRequest
    ) {
        List<Product> products = productProductDtoMapper.productSummaryRequestDtoToProductList(calculateProductsRequest.products());
        ProductSummary productSummary = shopService.calculateProducts(products, calculateProductsRequest.deliveryAddress());
        return productProductDtoMapper.productSummaryToCalculateProductsResponse(productSummary);
    }
}
