package com.bank.credit_card.payments.dto;

import com.bank.credit_card.generic.commons.Currency;
import com.bank.credit_card.payments.commons.CategoryPayment;
import com.bank.credit_card.payments.commons.ChannelPayment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDto(
        Long paymentId,
        Long cardId,
        BigDecimal amount,
        Currency currency,
        LocalDateTime paymentDate,
        LocalDateTime paymentApprobationDate,
        ChannelPayment channel,
        CategoryPayment category
) {
}
