package ru.alfabank.practice.kagrishin.bankonboarding.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("bankonboarding.settings")
public class BusinessConfig {

    private Integer maxDiscount;

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

}
