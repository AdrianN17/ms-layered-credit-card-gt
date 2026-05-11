package com.bank.credit_card.consumption.dto;

import com.bank.credit_card.generic.enums.CurrencyEnum;

import java.math.BigDecimal;

public record ConsumptionRequestDto(
        String sellerName,
        CurrencyEnum currency,
        BigDecimal amount
) {}

