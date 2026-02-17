package com.bank.credit_card.clients.dto.request;

import com.bank.credit_card.clients.commons.CardStatus;
import com.bank.credit_card.generic.commons.Currency;

import java.math.BigDecimal;

public record CardAccountRequestDto(
        Long cardId,
        BigDecimal crediticialTotalAmount,
        BigDecimal debtTax,
        Currency currency,
        Short facturationDate,
        Short paymentDate,
        CardStatus cardStatus
) {
}
