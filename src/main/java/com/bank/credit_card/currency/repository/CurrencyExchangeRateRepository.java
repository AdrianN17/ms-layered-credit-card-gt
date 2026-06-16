package com.bank.credit_card.currency.repository;

import com.bank.credit_card.generic.enums.CurrencyEnum;

import java.math.BigDecimal;

public interface CurrencyExchangeRateRepository {
    BigDecimal getExchangeRate(CurrencyEnum from, CurrencyEnum to);
}

