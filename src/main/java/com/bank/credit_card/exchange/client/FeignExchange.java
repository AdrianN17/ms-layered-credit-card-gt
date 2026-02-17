package com.bank.credit_card.exchange.client;

import com.bank.credit_card.exchange.client.response.ExchangeResponse;
import com.bank.credit_card.exchange.config.FeignExchangeConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(value = "exchanges", url = "${exchanges.url}", configuration = FeignExchangeConfig.class)
public interface FeignExchange {
    @RequestMapping(method = RequestMethod.GET, value = "${exchanges.endpoint}")
    Optional<ExchangeResponse> getExchangeRate();
}
