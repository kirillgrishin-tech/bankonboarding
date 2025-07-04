package ru.alfabank.practice.kagrishin.bankonboarding.schedule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alfabank.practice.kagrishin.bankonboarding.BankonboardingApplicationTests;
import ru.alfabank.practice.kagrishin.bankonboarding.service.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduledTasksTest extends BankonboardingApplicationTests {

    @Autowired
    private ScheduledTasks scheduledTasks;

    @Autowired
    private ProductService productService;

    @Test
    public void expectTwoUnavailableProductsAfterInventory() {
        scheduledTasks.inventory();
        assertEquals(productService.getUnavailableProducts().size(), 2);
    }

    @Test
    public void expectTwoUnavailableProductsAfterTwoInventory() {
        scheduledTasks.inventory();
        scheduledTasks.inventory();
        assertEquals(productService.getUnavailableProducts().size(), 2);
    }
}
