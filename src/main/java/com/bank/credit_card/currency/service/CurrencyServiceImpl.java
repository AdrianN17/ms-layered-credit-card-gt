package com.bank.credit_card.currency.service;

import com.bank.credit_card.currency.repository.CurrencyExchangeRateRepository;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyExchangeRateRepository currencyExchangeRateRepository;

    @Override
    public BigDecimal get(CurrencyEnum currencyCard,
                          CurrencyEnum amountCurrency,
                          BigDecimal amount) {

        if (currencyCard.equals(amountCurrency)) {
            return amount;
        }

        BigDecimal rate = currencyExchangeRateRepository.getExchangeRate(amountCurrency, currencyCard);
        return rate.multiply(amount);
    }
}

