package ru.alfabank.practice.kagrishin.bankonboarding.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("bankonboarding.settings")
public class BusinessConfig {

    private Integer maxDiscount;
}
