package ru.alfabank.practice.kagrishin.bankonboarding.storage;

import org.springframework.stereotype.Component;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Discount;
import ru.alfabank.practice.kagrishin.bankonboarding.repository.DiscountRepository;

import java.util.List;

@Component
public class DiscountStorageImpl implements DiscountStorage {

    private final DiscountRepository discountRepository;

    public DiscountStorageImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public List<Discount> getDiscounts(String productId) {
        return discountRepository.findByApplicableProductIdsContainingAndIsEnableTrue(productId)
                .stream()
                .map(discountEntity -> new Discount(
                        discountEntity.getId(),
                        discountEntity.getName(),
                        discountEntity.getApplicableProductIds(),
                        discountEntity.getPercent(),
                        discountEntity.isEnable(),
                        discountEntity.getCreated()
                        )
                )
                .toList();
    }
}
