package com.bank.credit_card.card.dto;

import com.bank.credit_card.card.enums.CategoryCardEnum;
import com.bank.credit_card.card.enums.TypeCardEnum;
import com.bank.credit_card.generic.enums.CurrencyEnum;

import java.math.BigDecimal;

public record CardDtoRequest(
        Long cardId,
        Long cardAccountId,
        TypeCardEnum typeCard,
        CategoryCardEnum categoryCard,
        CurrencyEnum currency,
        BigDecimal creditTotal,
        BigDecimal debtTax,
        Short paymentDay
) {
}
