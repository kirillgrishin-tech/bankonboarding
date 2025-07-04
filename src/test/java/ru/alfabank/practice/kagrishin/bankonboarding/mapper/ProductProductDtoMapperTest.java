package ru.alfabank.practice.kagrishin.bankonboarding.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {ProductProductDtoMapperImpl.class})
@SpringBootTest
public class ProductProductDtoMapperTest {

    @Autowired
    private ProductProductDtoMapper productProductDtoMapper;

    @Test
    public void expectProductDtoListWithOneElementFromProductList() {
        String id = "123";
        String name = "Soap";
        BigDecimal price = BigDecimal.valueOf(1221);
        Boolean isAvailable = true;
        Integer quantity = 21;
        var productList = List.of(
                Product.builder().id(id).name(name).price(price).isAvailable(isAvailable).quantity(quantity).build()
        );

        var productDtoList = productProductDtoMapper.productListToProductDtoList(productList);

        assertEquals(productDtoList.size(), 1);
        assertEquals(productDtoList.getFirst().id(), id);
        assertEquals(productDtoList.getFirst().name(), name);
        assertEquals(productDtoList.getFirst().price(), price);
    }

    @Test
    public void expectProductItemDtoListWithOneElementFromProductList() {
        String id = "123";
        String name = "Soap";
        BigDecimal price = BigDecimal.valueOf(1221);
        Boolean isAvailable = true;
        Integer quantity = 21;
        var productList = List.of(
                Product.builder().id(id).name(name).price(price).isAvailable(isAvailable).quantity(quantity).build()
        );

        var productItemDtoList = productProductDtoMapper.productListToProductItemDtoList(productList);

        assertEquals(productItemDtoList.size(), 1);
        assertEquals(productItemDtoList.getFirst().id(), id);
        assertEquals(productItemDtoList.getFirst().name(), name);
        assertEquals(productItemDtoList.getFirst().price(), price);
        assertEquals(productItemDtoList.getFirst().quantity(), quantity);
    }
}