package com.bank.credit_card.payments.util;

import com.bank.credit_card.payments.commons.CategoryPayment;
import com.bank.credit_card.payments.commons.ChannelPayment;
import lombok.experimental.UtilityClass;
import org.mapstruct.Named;

@UtilityClass
public class EnumPaymentUtility {

    @Named("mapToChannelPayment")
    public ChannelPayment mapToChannelPayment(String code) {
        return ChannelPayment.fromCode(code);
    }

    @Named("mapToCategoryPayment")
    public CategoryPayment mapToCategoryPayment(String code) {
        return CategoryPayment.fromCode(code);
    }
}
