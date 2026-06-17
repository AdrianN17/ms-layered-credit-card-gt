package com.bank.credit_card.currency.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.ReactorClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class RestClientConfig {

    private RestClient buildRestClient(String apiUrl, int connectTimeoutMs, int readTimeoutSec) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeoutMs)
                .responseTimeout(Duration.ofSeconds(readTimeoutSec))
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(readTimeoutSec, TimeUnit.SECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(readTimeoutSec, TimeUnit.SECONDS)));

        return RestClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Accept", "application/json")
                .requestFactory(new ReactorClientHttpRequestFactory(httpClient))
                .build();
    }

    @Bean
    @Profile("old")
    public RestClient restClientOld(
            @Value("${api.convert.url}") String apiUrl,
            @Value("${api.convert.connect-timeout-ms:3000}") int connectTimeoutMs,
            @Value("${api.convert.read-timeout-sec:5}") int readTimeoutSec) {
        log.info("[old] currency apiUrl => {} | connectTimeout={}ms | readTimeout={}s",
                apiUrl, connectTimeoutMs, readTimeoutSec);
        return buildRestClient(apiUrl, connectTimeoutMs, readTimeoutSec);
    }

    @Bean
    @Profile("new")
    public RestClient restClientNew(
            @Value("${api.convert.url}") String apiUrl,
            @Value("${api.convert.connect-timeout-ms:3000}") int connectTimeoutMs,
            @Value("${api.convert.read-timeout-sec:5}") int readTimeoutSec) {
        log.info("[new] currency apiUrl => {} | connectTimeout={}ms | readTimeout={}s",
                apiUrl, connectTimeoutMs, readTimeoutSec);
        return buildRestClient(apiUrl, connectTimeoutMs, readTimeoutSec);
    }
}
