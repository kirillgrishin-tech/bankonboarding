package ru.alfabank.practice.kagrishin.bankonboarding.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alfabank.practice.kagrishin.bankonboarding.BankonboardingApplicationTests;
import ru.alfabank.practice.kagrishin.bankonboarding.exception.AddressNotFoundException;
import ru.alfabank.practice.kagrishin.bankonboarding.exception.ProductNotFoundException;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.stub.DadataClientStub;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShopServiceTest extends BankonboardingApplicationTests {

    @Autowired
    private ShopService shopService;

    @Test
    public void expectSevenProductsAndButterProduct() {
        List<Product> products = shopService.getAllProducts();
        Product butter = products.stream().filter(product ->
                product.getName().equals("Butter")
                        && product.getPrice().equals(BigDecimal.valueOf(450.43))
        ).findFirst().get();

        assertEquals(products.size(), 7);
        assertNotNull(butter);
    }

    @Test
    public void expectSumOfProductsWithDiscount() {
        //prepare for calculate: one coke-cola, three butters and two apples with discounts
        DadataClientStub.returnValidAddressWithNineFialLevel();
        List<Product> products = new ArrayList<>();
        shopService.getAllProducts().forEach(product -> {
            switch (product.getName()) {
                case "Coke-Cola":
                    products.add(Product.builder().id(product.getId()).quantity(1).build());
                    break;
                case "Butter":
                    products.add(Product.builder().id(product.getId()).quantity(3).build());
                    break;
                case "Apple":
                    products.add(Product.builder().id(product.getId()).quantity(2).build());
                    break;
                default:
                    break;
            }
        });
        BigDecimal sum = shopService.calculateProducts(products, "").getSum();

        //sum must be 1580.15
        assertEquals(sum, BigDecimal.valueOf(1587.68));
    }

    @Test
    public void expectProductNotFoundException() {
        DadataClientStub.returnValidAddressWithNineFialLevel();

        assertThrows(ProductNotFoundException.class, () -> shopService.calculateProducts(
                List.of(Product.builder().id("adsasd").quantity(2).build()),
                ""
        ));
    }

    @Test
    public void expectAddressNotFoundException() {
        DadataClientStub.returnEmptyResponse();

        assertThrows(AddressNotFoundException.class, () -> shopService.calculateProducts(
                List.of(Product.builder().id("adsasd").quantity(2).build()), ""));
    }
}
