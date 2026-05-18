package com.bank.credit_card.payment.converter;

import com.bank.credit_card.generic.converter.AbstractEnumConverter;
import com.bank.credit_card.payment.enums.ChannelPaymentEnum;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ChannelPaymentEnumConverter extends AbstractEnumConverter<ChannelPaymentEnum> {
    public ChannelPaymentEnumConverter() {
        super(ChannelPaymentEnum::ofValue);
    }
}
