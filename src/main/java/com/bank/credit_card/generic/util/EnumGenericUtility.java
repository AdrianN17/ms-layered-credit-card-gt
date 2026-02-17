package com.bank.credit_card.generic.util;

import com.bank.credit_card.generic.commons.Currency;
import lombok.experimental.UtilityClass;
import org.mapstruct.Named;

@UtilityClass
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
