package com.bank.credit_card.clients.dto.response;

import com.bank.credit_card.generic.commons.Currency;

import java.math.BigDecimal;

public record CardAccountResponseDto(
        Long cardAccountId,
        BigDecimal crediticialTotalAmount,
        BigDecimal debtTax,
        Currency currency,
        Short facturationDate,
        Short paymentDate
) {
}
