package com.bank.credit_card.balance.dto;

import com.bank.credit_card.generic.enums.CurrencyEnum;

import java.math.BigDecimal;

public record BalanceDtoRequest(
    Long id,
    CurrencyEnum currency,
    BigDecimal exchangeRate,
    Long cardId,
    BigDecimal total,
    Short paymentDay
) {}
