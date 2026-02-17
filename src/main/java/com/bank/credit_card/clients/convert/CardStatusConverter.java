package com.bank.credit_card.clients.convert;

import com.bank.credit_card.clients.commons.CardStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CardStatusConverter implements AttributeConverter<CardStatus, Short> {

    @Override
    public Short convertToDatabaseColumn(CardStatus attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public CardStatus convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : CardStatus.ofValue(dbData.intValue()).orElse(null);
    }
}
