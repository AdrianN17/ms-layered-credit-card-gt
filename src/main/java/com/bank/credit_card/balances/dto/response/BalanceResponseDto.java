package com.bank.credit_card.balances.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BalanceResponseDto(
        Long cardId,

        Long balanceId,

        BigDecimal consumptionAmount,

        BigDecimal paymentAmount,

        LocalDate startDate,

        LocalDate endDate,

        BigDecimal exchangeRate,

        List<BalanceDataResponseDto> balanceDataset
) {
}
