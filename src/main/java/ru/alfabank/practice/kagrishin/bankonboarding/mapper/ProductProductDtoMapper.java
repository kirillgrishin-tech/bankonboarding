package ru.alfabank.practice.kagrishin.bankonboarding.mapper;

import org.springframework.stereotype.Component;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.ProductSummary;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ProductDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ProductItemDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ProductSummaryDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ProductSummaryResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProductProductDtoMapper {

    public List<ProductDto> productListToProductDtoList(List<Product> productList) {
        if (Objects.isNull(productList)) {
            return new ArrayList<>();
        }
        List<ProductDto> productDtoList = new ArrayList<>();
        productList.forEach(product ->
                productDtoList.add(new ProductDto(product.getId(), product.getName(), product.getPrice())));
        return productDtoList;
    }

    public List<ProductItemDto> productListToProductItemDtoList(List<Product> productList) {
        if (Objects.isNull(productList)) {
            return new ArrayList<>();
        }
        List<ProductItemDto> productItemDtoList = new ArrayList<>();
        productList.forEach(product ->
                productItemDtoList.add(new ProductItemDto(product.getId(), product.getName(), product.getPrice(), product.getQuantity())));
        return productItemDtoList;
    }

    public List<Product> productSummaryRequestDtoToProductList(
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

    public ProductSummaryResponse productSummaryToProductSummaryResponseDto(ProductSummary productSummary) {
        if (Objects.isNull(productSummary)) {
            return null;
        }
        return new ProductSummaryResponse(productSummary.getSum(), productListToProductItemDtoList(productSummary.getProducts()));
    }
}
