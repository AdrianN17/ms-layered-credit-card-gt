package com.bank.credit_card.clients.convert;

import com.bank.credit_card.clients.commons.TypeCard;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TypeCardConverter implements AttributeConverter<TypeCard, Short> {

    @Override
    public Short convertToDatabaseColumn(TypeCard attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public TypeCard convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : TypeCard.ofValue(dbData.intValue()).orElse(null);
    }
}

