package com.bank.credit_card.currency.repository;

import com.bank.credit_card.currency.dto.ExchangeRateApiDto;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.generic.exception.BadGatewayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

import static com.bank.credit_card.currency.exception.CurrencyErrorMessage.CURRENCY_NOT_FOUND;
import static com.bank.credit_card.currency.exception.CurrencyErrorMessage.CURRENCY_WS_ERROR;
import static java.util.Objects.isNull;

@Repository
@Profile("new")
public class CurrencyNewWSRepository implements CurrencyExchangeRateRepository {

    private final RestClient restClient;
    private final String apiKey;

    public CurrencyNewWSRepository(RestClient restClient,
                                   @Value("${api.convert.api-key}") String apiKey) {
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    @Override
    public BigDecimal getExchangeRate(CurrencyEnum from, CurrencyEnum to) {
        try {
            ExchangeRateApiDto response = restClient
                    .get()
                    .uri("/{apiKey}/latest/{base}", apiKey, from.getCode())
                    .retrieve()
                    .body(ExchangeRateApiDto.class);

            if (response == null || response.conversionRates() == null)
                throw new BadGatewayException(String.format(CURRENCY_WS_ERROR, from.getCode()));

            BigDecimal rate = response.conversionRates().get(to.getCode());
            if (isNull(rate))
                throw new BadGatewayException(String.format(CURRENCY_NOT_FOUND, to.getCode()));

            return rate;
        } catch (BadGatewayException e) {
            throw e;
        } catch (Exception e) {
            throw new BadGatewayException(String.format(CURRENCY_WS_ERROR, from.getCode()), e);
        }
    }
}

