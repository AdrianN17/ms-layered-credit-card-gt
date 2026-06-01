package com.bank.credit_card.balance.dto;

import com.bank.credit_card.generic.enums.CurrencyEnum;

import java.math.BigDecimal;

public record BalanceDtoRequest(
        Long idBalance,
    CurrencyEnum currency,
    Long cardId,
    BigDecimal total,
    Short paymentDay
) {}
