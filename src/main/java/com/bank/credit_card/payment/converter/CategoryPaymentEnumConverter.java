package com.bank.credit_card.payment.converter;

import com.bank.credit_card.generic.converter.AbstractEnumConverter;
import com.bank.credit_card.payment.enums.CategoryPaymentEnum;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryPaymentEnumConverter extends AbstractEnumConverter<CategoryPaymentEnum> {
    public CategoryPaymentEnumConverter() {
        super(CategoryPaymentEnum::ofValue);
    }
}
