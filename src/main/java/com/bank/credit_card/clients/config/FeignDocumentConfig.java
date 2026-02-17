package com.bank.credit_card.clients.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignDocumentConfig {

    @Value("${documents.authorization:}")
    private String authorization;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            if (authorization != null && !authorization.isBlank()) {
                requestTemplate.header("Authorization", authorization);
            }
        };
    }
}

