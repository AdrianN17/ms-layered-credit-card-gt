package com.bank.credit_card.payment.dto;

import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.payment.enums.CategoryPaymentEnum;
import com.bank.credit_card.payment.enums.ChannelPaymentEnum;

import java.math.BigDecimal;

public record PaymentRequestDto(
        ChannelPaymentEnum channel,
        CurrencyEnum currency,
        BigDecimal amount,
        CategoryPaymentEnum category,
        Integer pointsUsed
) {}
