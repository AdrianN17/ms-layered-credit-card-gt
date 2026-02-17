package com.bank.credit_card.balances.dto.request;

import com.bank.credit_card.generic.commons.Currency;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record BalanceRequestDto(
        Long idBalance,
        Long cardId,
        BigDecimal totalAmount,
        BigDecimal availableAmount,
        BigDecimal oldAmount,
        BigDecimal consumptionAmount,
        BigDecimal paymentAmount,
        BigDecimal exchangeRate,
        LocalDate startDate,
        LocalDate endDate,
        Currency currency
) {
}
