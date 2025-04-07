package ru.alfabank.practice.kagrishin.bankonboarding.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("bankonboarding")
public class BusinessConfig {

    private Integer maxDiscount;

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public Integer getMaxDiscount() {
        return maxDiscount;
    }
}
