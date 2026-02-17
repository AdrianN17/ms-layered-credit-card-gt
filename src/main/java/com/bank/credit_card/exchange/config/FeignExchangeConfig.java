package com.bank.credit_card.exchange.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignExchangeConfig {

    @Value("${exchanges.apiKey:}")
    private String apiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            if (apiKey != null && !apiKey.isBlank()) {
                requestTemplate.header("X-API-Key", apiKey);
            }
        };
    }
}

