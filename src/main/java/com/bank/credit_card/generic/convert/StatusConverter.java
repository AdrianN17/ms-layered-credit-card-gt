package com.bank.credit_card.generic.convert;

import com.bank.credit_card.generic.commons.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Short> {

    @Override
    public Short convertToDatabaseColumn(Status attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public Status convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : Status.ofValue(dbData.intValue()).orElse(null);
    }

    // Métodos estáticos para que MapStruct pueda mapear automáticamente
    public static Status map(Short value) {
        return value == null ? null : Status.ofValue(value.intValue()).orElse(null);
    }

    public static Short map(Status status) {
        return status == null ? null : (short) status.getValue();
    }
}
