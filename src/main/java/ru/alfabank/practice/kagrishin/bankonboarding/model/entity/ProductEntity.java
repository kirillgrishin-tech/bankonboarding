package ru.alfabank.practice.kagrishin.bankonboarding.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@NoArgsConstructor
@Document(collection = "products")
public class ProductEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String name;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;
    private Boolean isAvailable;

    public ProductEntity(String id, String name, BigDecimal price, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }
}
