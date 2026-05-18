package com.bank.credit_card.payment.dto;

import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.payment.enums.CategoryPaymentEnum;
import com.bank.credit_card.payment.enums.ChannelPaymentEnum;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequestDto(
        UUID paymentId,
        ChannelPaymentEnum channel,
        CurrencyEnum currency,
        BigDecimal amount,
        CategoryPaymentEnum category,
        Integer pointsUsed,
        Long cardId
) {
    public static PaymentRequestDto of(ChannelPaymentEnum channel, CurrencyEnum currency, BigDecimal amount,
                                       CategoryPaymentEnum category, Integer pointsUsed, Long cardId) {
        return new PaymentRequestDto(UUID.randomUUID(), channel, currency, amount, category, pointsUsed, cardId);
    }
}
