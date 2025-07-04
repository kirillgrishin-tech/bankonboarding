package ru.alfabank.practice.kagrishin.bankonboarding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alfabank.practice.kagrishin.bankonboarding.logging.Log;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Discount;
import ru.alfabank.practice.kagrishin.bankonboarding.repository.DiscountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;

    @Log
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
                ).toList();
    }
}
