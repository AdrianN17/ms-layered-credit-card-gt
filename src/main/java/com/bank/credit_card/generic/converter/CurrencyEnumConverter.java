package com.bank.credit_card.generic.converter;

import com.bank.credit_card.generic.enums.CurrencyEnum;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CurrencyEnumConverter extends AbstractEnumConverter<CurrencyEnum> {
    public CurrencyEnumConverter() {
        super(CurrencyEnum::ofValue);
    }
}