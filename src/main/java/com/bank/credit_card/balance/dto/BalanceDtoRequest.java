package com.bank.credit_card.balance.dto;

import com.bank.credit_card.generic.enums.CurrencyEnum;

import java.math.BigDecimal;

public record BalanceDtoRequest(
    CurrencyEnum currency,
    Long cardId,
    BigDecimal total,
    Short paymentDay
) {}
