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
    public void inventoryTaskTest() {
        scheduledTasks.inventory();
        assertEquals(productService.getUnavailableProducts().size(), 2);
    }
}
