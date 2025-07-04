package ru.alfabank.practice.kagrishin.bankonboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnableAspectJAutoProxy
public class BankonboardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankonboardingApplication.class, args);
    }
}
