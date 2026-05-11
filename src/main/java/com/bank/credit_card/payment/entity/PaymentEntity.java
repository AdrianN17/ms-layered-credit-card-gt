package com.bank.credit_card.payment.entity;

import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.payment.enums.CategoryPaymentEnum;
import com.bank.credit_card.payment.enums.ChannelPaymentEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface PaymentEntity {

    UUID getPaymentId();

    String getCardId();

    BigDecimal getAmount();

    CurrencyEnum getCurrency();

    LocalDateTime getPaymentDate();

    LocalDateTime getPaymentApprobationDate();

    ChannelPaymentEnum getChannel();

    CategoryPaymentEnum getCategory();
}
