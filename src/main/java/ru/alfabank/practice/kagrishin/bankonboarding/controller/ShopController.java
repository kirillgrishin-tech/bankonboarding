package ru.alfabank.practice.kagrishin.bankonboarding.controller;

import org.springframework.web.bind.annotation.*;
import ru.alfabank.practice.kagrishin.bankonboarding.mapper.controller.ProductControllerMapper;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.controller.ProductDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.controller.ProductSummaryDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.controller.ProductSummaryResponseDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.controller.WelcomeResponseDto;
import ru.alfabank.practice.kagrishin.bankonboarding.service.ShopService;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ProductControllerMapper productControllerMapper;
    private final ShopService shopService;

    public ShopController(ShopService shopService, ProductControllerMapper productControllerMapper) {
        this.shopService = shopService;
        this.productControllerMapper = productControllerMapper;
    }

    @GetMapping("/welcome")
    public WelcomeResponseDto welcome() {
        String message = shopService.getWelcomeMessage();
        return new WelcomeResponseDto(message);
    }

    @GetMapping("/product")
    public List<ProductDto> getAllProducts() {
        return productControllerMapper.productListToProductDtoList(shopService.getAllProducts());
    }

    @PostMapping("/calc")
    public ProductSummaryResponseDto calculateProducts(
            @RequestBody(required = false) List<ProductSummaryDto> productDtoList
    ) {
        List<Product> products = productControllerMapper.productSummaryRequestDtoToProductList(productDtoList);
        return productControllerMapper.productSummaryToProductSummaryResponseDto(shopService.calculateProducts(products));
    }
}
