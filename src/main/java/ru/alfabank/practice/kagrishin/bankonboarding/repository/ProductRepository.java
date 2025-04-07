package ru.alfabank.practice.kagrishin.bankonboarding.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductEntity;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    List<ProductEntity> findByIsAvailableTrue();
}
