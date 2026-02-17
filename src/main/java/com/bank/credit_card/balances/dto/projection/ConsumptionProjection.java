package com.bank.credit_card.balances.dto.projection;

import com.bank.credit_card.generic.commons.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.bank.credit_card.balances.commons.TypeTransaction.CONSUMPTION;

public interface ConsumptionProjection {
    BigDecimal getAmout();

    Short getCurrency();

    LocalDateTime getDate();

    String getSellerName();

    default Currency getCurrencyEnum() {
        return Currency.fromValue(getCurrency());
    }

    default String getTypeTransaccion() {
        return CONSUMPTION.getValue();
    }
}
