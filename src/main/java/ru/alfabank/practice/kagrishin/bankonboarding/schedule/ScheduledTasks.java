package ru.alfabank.practice.kagrishin.bankonboarding.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Product;
import ru.alfabank.practice.kagrishin.bankonboarding.storage.ProductStorage;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@ConditionalOnProperty(name = "scheduler.enabled", havingValue = "true")
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private final ProductStorage productStorage;

    public ScheduledTasks(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    @Scheduled(fixedRate = 60000)
    public void inventory() {
        for (int i = 0; i<2; i++) {
            List<Product> productDtoList = productStorage.getAvailableProducts();
            if (productDtoList.isEmpty()) {
                break;
            }
            int randomIndex = ThreadLocalRandom.current().nextInt(productDtoList.size());
            Product product = productDtoList.get(randomIndex);
            log.info("Change availability for product {} with id: {}", product.getName(), product.getId());
            product.setAvailable(false);
            productStorage.save(product);
        }
    }
}
