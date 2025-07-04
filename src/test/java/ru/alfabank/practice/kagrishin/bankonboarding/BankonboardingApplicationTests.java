package ru.alfabank.practice.kagrishin.bankonboarding;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.webservices.server.AutoConfigureMockWebServiceClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.alfabank.practice.kagrishin.bankonboarding.repository.DiscountRepository;
import ru.alfabank.practice.kagrishin.bankonboarding.repository.ProductRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.springframework.test.annotation.DirtiesContext.HierarchyMode.CURRENT_LEVEL;

@SpringBootTest
@Testcontainers
@AutoConfigureWireMock(port = 0)
@AutoConfigureMockWebServiceClient
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@DirtiesContext(hierarchyMode = CURRENT_LEVEL)
public abstract class BankonboardingApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void init() {
        System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
    }

    @BeforeEach
    public void beforeEach() throws IOException {
        initMongoDbData();
        wireMockServer.resetAll();
    }

    private void initMongoDbData() throws IOException {
        productRepository.deleteAll();
        discountRepository.deleteAll();
        productRepository.saveAll(readJsonFile("products.json", new TypeReference<>() {
        }));
        discountRepository.saveAll(readJsonFile("discounts.json", new TypeReference<>() {
        }));
    }

    public <T> List<T> readJsonFile(String filename, TypeReference<List<T>> typeReference) throws IOException {
        byte[] jsonData = Files.readAllBytes(new ClassPathResource(filename).getFile().toPath());
        return objectMapper.readValue(jsonData, typeReference);
    }

    public String readStringFromFile(String path) throws IOException {
        return Files.readString(new ClassPathResource(path).getFile().toPath());
    }
}
