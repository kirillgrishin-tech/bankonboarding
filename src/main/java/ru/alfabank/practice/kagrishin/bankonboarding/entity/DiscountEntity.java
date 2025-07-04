package ru.alfabank.practice.kagrishin.bankonboarding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Document(collection = "discounts")
public class DiscountEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String name;
    @Field(targetType = FieldType.ARRAY)
    private List<String> applicableProductIds;
    @Field(targetType = FieldType.INT32)
    private Integer percent;
    @Field(targetType = FieldType.BOOLEAN)
    private Boolean isEnable;
    @Field(targetType = FieldType.DATE_TIME)
    private LocalDateTime created;
}
