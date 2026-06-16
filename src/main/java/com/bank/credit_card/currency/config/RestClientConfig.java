package com.bank.credit_card.currency.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
public class RestClientConfig {

    @Bean
    @Profile("old")
    public RestClient restClientOld(@Value("${api.convert.url}") String apiUrl) {
        log.info("[old] currency apiUrl => {}", apiUrl);
        return RestClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Bean
    @Profile("new")
    public RestClient restClientNew(@Value("${api.convert.url}") String apiUrl) {
        log.info("[new] currency apiUrl => {}", apiUrl);
        return RestClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
