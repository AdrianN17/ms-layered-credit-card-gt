package com.bank.credit_card.payment.converter;

import com.bank.credit_card.payment.enums.CategoryPaymentEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryPaymentEnumConverter implements AttributeConverter<CategoryPaymentEnum, Short> {

    @Override
    public Short convertToDatabaseColumn(CategoryPaymentEnum attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public CategoryPaymentEnum convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : CategoryPaymentEnum.ofValue(dbData.intValue()).orElse(null);
    }
}
