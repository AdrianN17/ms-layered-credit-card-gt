package com.bank.credit_card.consumptions.dto;

import com.bank.credit_card.generic.commons.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ConsumptionDto(
        Long consumptionId,
        Long cardId,
        String sellerName,
        Currency currency,
        BigDecimal amount,
        LocalDateTime consumptionDate,
        LocalDateTime consumptionApprobationDae
) {
}
