package com.bank.credit_card.balances.dto.projection;

import com.bank.credit_card.generic.commons.Currency;
import com.bank.credit_card.payments.commons.CategoryPayment;
import com.bank.credit_card.payments.commons.ChannelPayment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.bank.credit_card.balances.commons.TypeTransaction.PAYMENT;

public interface PaymentProjection {
    BigDecimal getAmout();

    Short getCurrency();

    LocalDateTime getDate();

    Short getChannel();

    Short getCategory();


    default Currency getCurrencyEnum() {
        return Currency.fromValue(getCurrency());
    }

    default ChannelPayment getChannelEnum() {
        return ChannelPayment.fromValue(getChannel());
    }

    default CategoryPayment getCategoryEnum() {
        return CategoryPayment.fromValue(getCategory());
    }

    default String getOrigin() {
        return getChannelEnum().name() + " - " + getCategoryEnum().name();
    }

    default String getTypeTransaccion() {
        return PAYMENT.getValue();
    }
}
