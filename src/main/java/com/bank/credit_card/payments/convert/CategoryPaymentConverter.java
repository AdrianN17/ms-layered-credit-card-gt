package com.bank.credit_card.payments.convert;

import com.bank.credit_card.payments.commons.CategoryPayment;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryPaymentConverter implements AttributeConverter<CategoryPayment, Short> {

    @Override
    public Short convertToDatabaseColumn(CategoryPayment attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public CategoryPayment convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : CategoryPayment.ofValue(dbData.intValue()).orElse(null);
    }
}

