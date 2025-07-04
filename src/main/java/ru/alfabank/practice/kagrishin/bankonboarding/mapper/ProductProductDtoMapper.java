package ru.alfabank.practice.kagrishin.bankonboarding.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.model.ProductSummary;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.CalculateProductsResponse;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ProductDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ProductItemDto;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.ProductSummaryDto;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ProductProductDtoMapper {

    List<ProductDto> productListToProductDtoList(List<Product> productList);

    List<ProductItemDto> productListToProductItemDtoList(List<Product> productList);

    List<Product> productSummaryRequestDtoToProductList(List<ProductSummaryDto> productSummaryDtoList);

    CalculateProductsResponse productSummaryToCalculateProductsResponse(ProductSummary productSummary);
}
