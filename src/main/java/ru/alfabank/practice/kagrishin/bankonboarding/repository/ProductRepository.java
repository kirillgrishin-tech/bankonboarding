package ru.alfabank.practice.kagrishin.bankonboarding.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.alfabank.practice.kagrishin.bankonboarding.model.repository.ProductDocument;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductDocument, String> {

    List<ProductDocument> findByIsAvailableTrue();
}
