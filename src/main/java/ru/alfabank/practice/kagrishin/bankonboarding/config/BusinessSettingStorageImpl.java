package ru.alfabank.practice.kagrishin.bankonboarding.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.alfabank.practice.kagrishin.bankonboarding.storage.BusinessSettingStorage;

@Component
@ConfigurationProperties("settings")
public class BusinessSettingStorageImpl implements BusinessSettingStorage {

    private int maxDiscount;

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    @Override
    public int getMaxDiscount() {
        return maxDiscount;
    }
}
