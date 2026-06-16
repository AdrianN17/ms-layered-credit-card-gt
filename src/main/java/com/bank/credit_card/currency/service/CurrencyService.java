package com.bank.credit_card.currency.service;

import com.bank.credit_card.generic.enums.CurrencyEnum;

import java.math.BigDecimal;

@FunctionalInterface
public interface CurrencyService {
    BigDecimal get(CurrencyEnum currencyCard,
                    CurrencyEnum amountCurrency,
                    BigDecimal amount);
}
