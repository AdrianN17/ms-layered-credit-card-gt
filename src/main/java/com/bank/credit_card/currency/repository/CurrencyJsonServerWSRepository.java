package com.bank.credit_card.currency.repository;

import com.bank.credit_card.currency.dto.CurrencyDto;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.generic.exception.BadGatewayException;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.Optional;

import static com.bank.credit_card.currency.exception.CurrencyErrorMessage.CURRENCY_WS_ERROR;

@Repository
public class CurrencyJsonServerWSRepository {

    private final RestClient restClient;

    public CurrencyJsonServerWSRepository(RestClient restClient) {
        this.restClient = restClient;
    }

    public Optional<CurrencyDto> findByCurrency(CurrencyEnum currency) {
        try {
            return Optional.ofNullable(restClient
                    .get()
                    .uri("/" + currency.getCode())
                    .retrieve()
                    .body(CurrencyDto.class));
        } catch (Exception e) {
            throw new BadGatewayException(String.format(CURRENCY_WS_ERROR, currency.getCode()), e);
        }
    }
}