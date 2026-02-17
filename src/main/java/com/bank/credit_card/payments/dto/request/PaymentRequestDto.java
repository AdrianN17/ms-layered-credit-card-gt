package com.bank.credit_card.payments.dto.request;

import com.bank.credit_card.generic.commons.Currency;
import com.bank.credit_card.payments.commons.CategoryPayment;
import com.bank.credit_card.payments.commons.ChannelPayment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentRequestDto(
        Long cardId,
        BigDecimal amount,
        Currency currency,
        ChannelPayment channel,
        CategoryPayment category,
        LocalDateTime paymentDate
) {
}
