package ru.alfabank.practice.kagrishin.bankonboarding.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.service.ProductService;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
@Log4j2
@ConditionalOnProperty(name = "bankonboarding.scheduler.inventory.enabled", havingValue = "true")
public class ScheduledTasks {

    private final ProductService productService;

    @Scheduled(fixedRateString = "${bankonboarding.scheduler.inventory.delay}")
    public void inventory() {
        productService.getUnavailableProducts().forEach(product -> {
            product.setIsAvailable(true);
            productService.save(product);
        });
        for (int i = 0; i < 2; i++) {
            List<Product> productDtoList = productService.getAvailableProducts();
            if (productDtoList.isEmpty()) {
                break;
            }
            int randomIndex = ThreadLocalRandom.current().nextInt(productDtoList.size());
            Product product = productDtoList.get(randomIndex);
            log.info("Change availability for product {} with id: {}", product.getName(), product.getId());
            product.setIsAvailable(false);
            productService.save(product);
        }
    }
}
