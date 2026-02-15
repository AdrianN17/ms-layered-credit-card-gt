package com.bank.credit_card.balances.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BalanceDto(
        Long idBalance,
        Long cardId,
        BigDecimal consumptionAmount,
        BigDecimal paymentAmount,
        BigDecimal exchangeRate,
        LocalDate startDate,
        LocalDate endDate
) {
}
