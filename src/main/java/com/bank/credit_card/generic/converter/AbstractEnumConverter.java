package com.bank.credit_card.generic.converter;

import com.bank.credit_card.generic.enums.ValuedEnum;
import jakarta.persistence.AttributeConverter;

import java.util.Optional;
import java.util.function.Function;

public abstract class AbstractEnumConverter<E extends Enum<E> & ValuedEnum>
        implements AttributeConverter<E, Short> {

    private final Function<Integer, Optional<E>> resolver;

    protected AbstractEnumConverter(Function<Integer, Optional<E>> resolver) {
        this.resolver = resolver;
    }

    @Override
    public Short convertToDatabaseColumn(E attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public E convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : resolver.apply(dbData.intValue()).orElse(null);
    }
}

