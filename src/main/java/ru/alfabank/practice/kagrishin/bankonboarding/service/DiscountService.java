package ru.alfabank.practice.kagrishin.bankonboarding.service;

import org.springframework.stereotype.Component;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Discount;
import ru.alfabank.practice.kagrishin.bankonboarding.repository.DiscountRepository;

import java.util.List;

@Component
public class DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> getDiscounts(String productId) {
        return discountRepository.findByApplicableProductIdsContainingAndIsEnableTrue(productId)
                .stream()
                .map(discountEntity -> new Discount(
                        discountEntity.getId(),
                        discountEntity.getName(),
                        discountEntity.getApplicableProductIds(),
                        discountEntity.getPercent(),
                        discountEntity.getIsEnable(),
                        discountEntity.getCreated()
                        )
                )
                .toList();
    }
}
