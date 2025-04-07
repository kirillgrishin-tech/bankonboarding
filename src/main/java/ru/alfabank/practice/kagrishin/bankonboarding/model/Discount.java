package ru.alfabank.practice.kagrishin.bankonboarding.model;

import java.time.LocalDateTime;
import java.util.List;

public class Discount {

    private final String id;
    private String name;
    private List<String> applicableProductIds;
    private Integer percent;
    private Boolean isEnable;
    private LocalDateTime created;

    public Discount(String id, String name, List<String> applicableProductIds, int percent, boolean isEnable, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.applicableProductIds = applicableProductIds;
        this.percent = percent;
        this.isEnable = isEnable;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getApplicableProductIds() {
        return applicableProductIds;
    }

    public void setApplicableProductIds(List<String> applicableProductIds) {
        this.applicableProductIds = applicableProductIds;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public boolean getEnable() {
        return isEnable;
    }

    public void setIsEnable(boolean enable) {
        isEnable = enable;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
