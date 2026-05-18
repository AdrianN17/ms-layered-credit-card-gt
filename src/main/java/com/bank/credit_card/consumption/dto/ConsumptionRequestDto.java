package com.bank.credit_card.consumption.dto;

import com.bank.credit_card.generic.enums.CurrencyEnum;

import java.math.BigDecimal;
import java.util.UUID;

public record ConsumptionRequestDto(
        UUID consumptionId,
        String sellerName,
        CurrencyEnum currency,
        BigDecimal amount,
        Long cardId
) {
    public static ConsumptionRequestDto of(String sellerName, CurrencyEnum currency, BigDecimal amount, Long cardId) {
        return new ConsumptionRequestDto(UUID.randomUUID(), sellerName, currency, amount, cardId);
    }
}