package com.bank.credit_card.generic.convert;

import com.bank.credit_card.generic.commons.Currency;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CurrencyConverter implements AttributeConverter<Currency, Short> {

    @Override
    public Short convertToDatabaseColumn(Currency attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public Currency convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : Currency.ofValue(dbData.intValue()).orElse(null);
    }

    // Métodos estáticos para MapStruct
    public static Currency map(Short value) {
        return value == null ? null : Currency.ofValue(value.intValue()).orElse(null);
    }

    public static Short map(Currency currency) {
        return currency == null ? null : (short) currency.getValue();
    }
}
