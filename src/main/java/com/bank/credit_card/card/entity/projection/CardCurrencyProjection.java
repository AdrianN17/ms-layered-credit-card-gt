package com.bank.credit_card.card.entity.projection;

import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.generic.exception.BadRequestException;

import static com.bank.credit_card.card.exception.CardErrorMessage.INCORRECT_CURRENCY_VALUE;

public interface CardCurrencyProjection {

    Integer getCurrency();

    default CurrencyEnum getCurrencyEnum() {
        return CurrencyEnum.ofValue(getCurrency())
                .orElseThrow(() -> new BadRequestException(INCORRECT_CURRENCY_VALUE));
    }
}
