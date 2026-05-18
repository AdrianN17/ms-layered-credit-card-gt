package com.bank.credit_card.card.converter;

import com.bank.credit_card.card.enums.CardStatusEnum;
import com.bank.credit_card.generic.converter.AbstractEnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CardStatusEnumConverter extends AbstractEnumConverter<CardStatusEnum> {
    public CardStatusEnumConverter() {
        super(CardStatusEnum::ofValue);
    }
}
