package com.bank.credit_card.balances.dto.projection;

import com.bank.credit_card.generic.commons.Currency;

import java.math.BigDecimal;

public interface CardAccountDataProjection {
    Short getFacturationDate();
    BigDecimal getTotalAmount();
    Short getCurrency();

    default Currency getCurrencyEnum() {
        return Currency.fromValue(getCurrency());
    }
}
