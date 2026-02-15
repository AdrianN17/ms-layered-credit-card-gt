package com.bank.credit_card.clients.convert;

import com.bank.credit_card.clients.commons.CategoryCard;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryCardConverter implements AttributeConverter<CategoryCard, Short> {

    @Override
    public Short convertToDatabaseColumn(CategoryCard attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public CategoryCard convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : CategoryCard.ofValue(dbData.intValue()).orElse(null);
    }
}

