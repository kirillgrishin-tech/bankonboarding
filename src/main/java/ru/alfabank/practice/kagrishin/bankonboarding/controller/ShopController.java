package ru.alfabank.practice.kagrishin.bankonboarding.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.alfabank.practice.kagrishin.bankonboarding.mapper.ProductProductDtoMapper;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ProductDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ProductSummaryDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ProductSummaryResponse;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.WelcomeResponse;
import ru.alfabank.practice.kagrishin.bankonboarding.service.ShopService;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ProductProductDtoMapper productProductDtoMapper;
    private final ShopService shopService;

    public ShopController(ShopService shopService, ProductProductDtoMapper productProductDtoMapper) {
        this.shopService = shopService;
        this.productProductDtoMapper = productProductDtoMapper;
    }

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
    public ProductSummaryResponse calculateProducts(
            @Valid @RequestBody(required = false) List<ProductSummaryDto> productDtoList
    ) {
        List<Product> products = productProductDtoMapper.productSummaryRequestDtoToProductList(productDtoList);
        return productProductDtoMapper.productSummaryToProductSummaryResponseDto(shopService.calculateProducts(products));
    }
}
