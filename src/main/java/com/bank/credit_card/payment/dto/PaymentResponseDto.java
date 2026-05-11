package com.bank.credit_card.payment.dto;

import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.payment.enums.CategoryPaymentEnum;
import com.bank.credit_card.payment.enums.ChannelPaymentEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponseDto(
        ChannelPaymentEnum channel,
        CurrencyEnum currency,
        BigDecimal amount,
        CategoryPaymentEnum category,
        LocalDateTime paymentDate,
        LocalDateTime paymentApprobationDate
) {}
