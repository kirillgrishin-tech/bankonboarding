package ru.alfabank.practice.kagrishin.bankonboarding.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.alfabank.practice.kagrishin.bankonboarding.entity.DiscountEntity;

import java.util.List;

public interface DiscountRepository extends MongoRepository<DiscountEntity, String> {

    List<DiscountEntity> findByApplicableProductIdsContainingAndIsEnableTrue(String productId);
}
