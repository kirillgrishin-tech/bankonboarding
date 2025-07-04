package ru.alfabank.practice.kagrishin.bankonboarding.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;
import ru.alfabank.practice.kagrishin.bankonboarding.BankonboardingApplicationTests;
import ru.alfabank.practice.kagrishin.bankonboarding.stub.DadataClientStub;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShopControllerTest extends BankonboardingApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void expectWelcomeMessageAndStatusOK() throws Exception {
        mvc.perform(get("/shop/welcome"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void expectProductsAndStatusOK() throws Exception {
        String expectedResponse = readStringFromFile("controller/products-response.json");

        mvc.perform(get("/shop/product"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponse, JsonCompareMode.STRICT))
                .andDo(print());
    }

    @Test
    public void expectCalcProductsErrorWithBadRequestStatus() throws Exception {
        DadataClientStub.defaultStub();
        String expectedResponse = readStringFromFile("controller/calc-products-bad-request-response.json");
        String request = readStringFromFile("controller/calc-products-bad-request-request.json");

        mvc.perform(post("/shop/calc").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse, JsonCompareMode.STRICT))
                .andDo(print());
    }

    @Test
    public void expectSumOfProductsWithStatusOK() throws Exception {
        DadataClientStub.defaultStub();
        String request = readStringFromFile("controller/calc-products-request.json");
        String expectedResponse = readStringFromFile("controller/calc-products-response.json");

        mvc.perform(post("/shop/calc").content(request).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponse, JsonCompareMode.STRICT))
                .andDo(print());
    }

    @Test
    public void expectEmptyDeliveryAddressErrorWhenSendCalcProductsRequest() throws Exception {
        String request = readStringFromFile("controller/calc-products-empty-address-request.json");
        String expectedResponse = readStringFromFile("controller/calc-products-empty-address-response.json");

        mvc.perform(post("/shop/calc").content(request).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponse, JsonCompareMode.STRICT))
                .andDo(print());
    }

    @Test
    public void expectUnauthorisedErrorProxiesFromDadata() throws Exception {
        String request = readStringFromFile("controller/calc-products-request.json");
        DadataClientStub.unauthorisedErrorResponse();

        mvc.perform(post("/shop/calc").content(request).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void expectAddressNotFoundBadRequestError() throws Exception {
        String request = readStringFromFile("controller/calc-products-request.json");
        String expectedResponse = readStringFromFile("controller/address-not-found-response.json");
        DadataClientStub.addressesWithoutFiasLevelNineCode();

        mvc.perform(post("/shop/calc").content(request).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponse, JsonCompareMode.STRICT))
                .andDo(print());
    }
}
