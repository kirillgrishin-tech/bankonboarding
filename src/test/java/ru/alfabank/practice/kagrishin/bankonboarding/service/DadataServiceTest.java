package ru.alfabank.practice.kagrishin.bankonboarding.service;

import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alfabank.practice.kagrishin.bankonboarding.BankonboardingApplicationTests;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Address;
import ru.alfabank.practice.kagrishin.bankonboarding.stub.DadataClientStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DadataServiceTest extends BankonboardingApplicationTests {

    @Autowired
    private DadataService dadataService;

    @Test
    public void expectOneAddress() {
        String address = "Москва Маршала Жукова 1";
        DadataClientStub.returnValidAddressWithNineFialLevel();

        List<Address> addresses = dadataService.getAddresses(address);

        assertEquals(addresses.size(), 1);
    }

    @Test
    public void expectNotFoundException() {
        String address = "Москва Маршала Жукова 1";

        assertThrows(FeignException.NotFound.class, () -> dadataService.getAddresses(address));
    }
}
