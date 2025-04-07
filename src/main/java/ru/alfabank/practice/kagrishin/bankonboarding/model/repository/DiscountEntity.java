package ru.alfabank.practice.kagrishin.bankonboarding.model.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Document(collection = "discounts")
public class DiscountEntity {

    @Id
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

    public DiscountEntity(String name, List<String> applicableProductIds, int percent, Boolean isEnable, LocalDateTime created) {
        this.name = name;
        this.applicableProductIds = applicableProductIds;
        this.percent = percent;
        this.isEnable = isEnable;
        this.created = created;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public List<String> getApplicableProductIds() {
        return applicableProductIds;
    }

    public void setApplicableProductIds(List<String> applicableProductIds) {
        this.applicableProductIds = applicableProductIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DiscountEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", applicableProductIds=" + applicableProductIds +
                ", percent=" + percent +
                ", isEnable=" + isEnable +
                ", created=" + created +
                '}';
    }
}
