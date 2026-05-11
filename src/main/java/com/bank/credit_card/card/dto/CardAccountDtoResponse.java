package com.bank.credit_card.card.dto;

import com.bank.credit_card.card.enums.CardStatusEnum;
import com.bank.credit_card.generic.enums.CurrencyEnum;

import java.math.BigDecimal;

public record CardAccountDtoResponse(
        BigDecimal creditTotal,
        BigDecimal debtTax,
        CurrencyEnum currency,
        Short paymentDate,
        CardStatusEnum cardStatus
) {}
