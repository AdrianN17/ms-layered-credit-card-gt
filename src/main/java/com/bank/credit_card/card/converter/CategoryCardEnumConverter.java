package com.bank.credit_card.card.converter;

import com.bank.credit_card.card.enums.CategoryCardEnum;
import com.bank.credit_card.generic.converter.AbstractEnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryCardEnumConverter extends AbstractEnumConverter<CategoryCardEnum> {
    public CategoryCardEnumConverter() {
        super(CategoryCardEnum::ofValue);
    }
}
