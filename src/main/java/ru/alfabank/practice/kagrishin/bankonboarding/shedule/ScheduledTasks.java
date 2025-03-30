package ru.alfabank.practice.kagrishin.bankonboarding.shedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDto;
import ru.alfabank.practice.kagrishin.bankonboarding.storage.ProductStorage;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private final ProductStorage productStorage;

    public ScheduledTasks(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    @Scheduled(fixedRate = 60000)
    public void inventory() {
        for (int i = 0; i<2; i++) {
            List<ProductDto> productDtoList = productStorage.getAvailableProducts();
            if (productDtoList.isEmpty()) {
                break;
            }
            int randomIndex = ThreadLocalRandom.current().nextInt(productDtoList.size());
            ProductDto product = productDtoList.get(randomIndex);
            log.info("Change availability for product {} with id: {}", product.name(), product.id());
            productStorage.save(new ProductDto(product.id(), product.name(), product.price(), false));
        }
    }
}
