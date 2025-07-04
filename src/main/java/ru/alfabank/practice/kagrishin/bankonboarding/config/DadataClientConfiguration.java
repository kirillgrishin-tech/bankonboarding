package ru.alfabank.practice.kagrishin.bankonboarding.config;

import feign.RequestInterceptor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wiremock.org.eclipse.jetty.http.HttpHeader;

import static java.lang.String.format;

@Configuration
@Getter
@Setter
public class DadataClientConfiguration {

    @Bean
    public RequestInterceptor interceptor(@Value("${dadata.token}") String token) {
        return template -> template.header(HttpHeader.AUTHORIZATION.asString(), format("Token %s", token));
    }
}
