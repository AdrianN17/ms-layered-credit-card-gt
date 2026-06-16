package com.bank.credit_card.currency.repository;

import com.bank.credit_card.currency.dto.CurrencyDto;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.generic.exception.BadGatewayException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

import static com.bank.credit_card.currency.exception.CurrencyErrorMessage.CURRENCY_NOT_FOUND;
import static com.bank.credit_card.currency.exception.CurrencyErrorMessage.CURRENCY_WS_ERROR;

@Repository
@Profile("old")
public class CurrencyOldWSRepository implements CurrencyExchangeRateRepository {

    private final RestClient restClient;

    public CurrencyOldWSRepository(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public BigDecimal getExchangeRate(CurrencyEnum from, CurrencyEnum to) {
        try {
            CurrencyDto dto = restClient
                    .get()
                    .uri("/" + from.getCode())
                    .retrieve()
                    .body(CurrencyDto.class);

            if (dto == null)
                throw new BadGatewayException(String.format(CURRENCY_NOT_FOUND, from.getCode()));

            return dto.value();
        } catch (BadGatewayException e) {
            throw e;
        } catch (Exception e) {
            throw new BadGatewayException(String.format(CURRENCY_WS_ERROR, from.getCode()), e);
        }
    }
}

