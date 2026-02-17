package com.bank.credit_card.consumptions.dto.request;

import com.bank.credit_card.generic.commons.Currency;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ConsumptionRequestDto(
        Long cardId,
        String sellerName,
        Currency currency,
        BigDecimal amount,
        LocalDateTime consumptionDate
) {
}
