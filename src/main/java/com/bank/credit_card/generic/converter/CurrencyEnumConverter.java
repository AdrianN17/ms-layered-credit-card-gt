package com.bank.credit_card.generic.converter;

import com.bank.credit_card.generic.enums.CurrencyEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CurrencyEnumConverter implements AttributeConverter<CurrencyEnum, Short> {

    @Override
    public Short convertToDatabaseColumn(CurrencyEnum attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public CurrencyEnum convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : CurrencyEnum.ofValue(dbData.intValue()).orElse(null);
    }
}