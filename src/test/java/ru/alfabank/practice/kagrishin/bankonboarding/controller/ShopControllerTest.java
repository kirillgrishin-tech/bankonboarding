package ru.alfabank.practice.kagrishin.bankonboarding.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.alfabank.practice.kagrishin.bankonboarding.BankonboardingApplicationTests;

import java.nio.file.Files;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShopControllerTest extends BankonboardingApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void welcomeTest() throws Exception {
        mvc.perform(get("/shop/welcome"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void getProductsTest() throws Exception {
        String expectedResponse = Files.readString(new ClassPathResource("controller/products-response.json").getFile().toPath());

        mvc.perform(get("/shop/product"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponse))
                .andDo(print());
    }

    @Test
    public void calcProductsBadRequestTest() throws Exception {
        mvc.perform(post("/shop/calc").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void calcProductsTest() throws Exception {
        String request = Files.readString(new ClassPathResource("controller/calc-products-request.json").getFile().toPath());
        String expectedResponse = Files.readString(new ClassPathResource("controller/calc-products-response.json").getFile().toPath());

        mvc.perform(post("/shop/calc").content(request).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponse))
                .andDo(print());
    }
}
