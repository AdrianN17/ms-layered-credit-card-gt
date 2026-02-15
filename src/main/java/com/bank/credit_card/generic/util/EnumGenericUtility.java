package com.bank.credit_card.generic.util;

import com.bank.credit_card.generic.commons.Currency;
import org.mapstruct.Named;

public class EnumGenericUtility {

    @Named("mapToCurrency")
    public Currency mapToCurrency(String code) {
        return Currency.fromCode(code);
    }

    @Named("currencyToString")
    public String currencyToString(Currency currency) {
        return currency.getCode();
    }
}
