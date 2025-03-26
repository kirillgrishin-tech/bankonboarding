package ru.alfabank.practice.kagrishin.bankonboarding.mapper.controller;

import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.ProductSummary;
import ru.alfabank.practice.kagrishin.bankonboarding.model.controller.ProductDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.controller.ProductItemDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.controller.ProductSummaryDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.controller.ProductSummaryResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductControllerMapper {

    private ProductControllerMapper() {
        throw new UnsupportedOperationException();
    }

    public static List<ProductDto> productListToProductDtoList(List<Product> productList) {
        if (Objects.isNull(productList)) {
            return new ArrayList<>();
        }
        List<ProductDto> productDtoList = new ArrayList<>();
        productList.forEach(product ->
                productDtoList.add(new ProductDto(product.getId(), product.getName(), product.getPrice())));
        return productDtoList;
    }

    public static List<ProductItemDto> productListToProductItemDtoList(List<Product> productList) {
        if (Objects.isNull(productList)) {
            return new ArrayList<>();
        }
        List<ProductItemDto> productItemDtoList = new ArrayList<>();
        productList.forEach(product ->
                productItemDtoList.add(new ProductItemDto(product.getId(), product.getName(), product.getPrice(), product.getQuantity())));
        return productItemDtoList;
    }

    public static List<Product> productSummaryRequestDtoToProductList(
            List<ProductSummaryDto> productSummaryDtoList
    ) {
        if (Objects.isNull(productSummaryDtoList)) {
            return new ArrayList<>();
        }
        List<Product> products = new ArrayList<>();
        productSummaryDtoList.forEach(
                productSummaryDto -> products.add(new Product(productSummaryDto.id(), productSummaryDto.quantity()))
        );
        return products;
    }

    public static ProductSummaryResponseDto productSummaryToProductSummaryResponseDto(ProductSummary productSummary) {
        if (Objects.isNull(productSummary)) {
            return null;
        }
        return new ProductSummaryResponseDto(productSummary.getSum(), productListToProductItemDtoList(productSummary.getProducts()));
    }
}
