package com.bank.credit_card.card.converter;

import com.bank.credit_card.card.enums.TypeCardEnum;
import com.bank.credit_card.generic.converter.AbstractEnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TypeCardEnumConverter extends AbstractEnumConverter<TypeCardEnum> {
    public TypeCardEnumConverter() {
        super(TypeCardEnum::ofValue);
    }
}
