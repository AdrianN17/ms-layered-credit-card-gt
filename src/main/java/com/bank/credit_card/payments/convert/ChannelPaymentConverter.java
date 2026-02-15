package com.bank.credit_card.payments.convert;

import com.bank.credit_card.payments.commons.ChannelPayment;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ChannelPaymentConverter implements AttributeConverter<ChannelPayment, Short> {

    @Override
    public Short convertToDatabaseColumn(ChannelPayment attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public ChannelPayment convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : ChannelPayment.ofValue(dbData.intValue()).orElse(null);
    }
}

