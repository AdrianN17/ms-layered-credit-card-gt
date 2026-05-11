package com.bank.credit_card.payment.converter;

import com.bank.credit_card.payment.enums.ChannelPaymentEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ChannelPaymentEnumConverter implements AttributeConverter<ChannelPaymentEnum, Short> {

    @Override
    public Short convertToDatabaseColumn(ChannelPaymentEnum attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public ChannelPaymentEnum convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : ChannelPaymentEnum.ofValue(dbData.intValue()).orElse(null);
    }
}
