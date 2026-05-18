package com.bank.credit_card.generic.converter;

import com.bank.credit_card.generic.enums.StatusEnum;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusEnumConverter extends AbstractEnumConverter<StatusEnum> {
    public StatusEnumConverter() {
        super(StatusEnum::ofValue);
    }
}