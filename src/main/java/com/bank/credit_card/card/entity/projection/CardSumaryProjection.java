package com.bank.credit_card.card.entity.projection;


import com.bank.credit_card.card.enums.CardStatusEnum;
import com.bank.credit_card.card.enums.CategoryCardEnum;
import com.bank.credit_card.card.enums.TypeCardEnum;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.generic.exception.BadRequestException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.bank.credit_card.card.exception.CardErrorMessage.*;

public interface CardSumaryProjection {

    Integer getTypeCard();

    Integer getCategoryCard();

    BigDecimal getCreditTotal();

    BigDecimal getDebtTax();

    Integer getCurrency();

    Integer getPaymentDate();

    Integer getCardStatus();

    Boolean getHasDiscount();

    Integer getTotalPoints();

    BigDecimal getMultiplierPoints();

    BigDecimal getTotalAmount();

    BigDecimal getAvailableAmount();

    BigDecimal getOldAmount();

    LocalDate getStartDate();

    LocalDate getEndDate();

    default TypeCardEnum getTypeCardEnum() {
        return TypeCardEnum.ofValue(getTypeCard())
                .orElseThrow(() -> new BadRequestException(INCORRECT_TYPE_CARD_VALUE));
    }

    default CategoryCardEnum getCategoryCardEnum() {
        return CategoryCardEnum.ofValue(getCategoryCard())
                .orElseThrow(() -> new BadRequestException(INCORRECT_CATEGORY_VALUE));
    }

    default CurrencyEnum getCurrencyEnum() {
        return CurrencyEnum.ofValue(getCurrency())
                .orElseThrow(() -> new BadRequestException(INCORRECT_CURRENCY_VALUE));
    }

    default CardStatusEnum getCardStatusEnum() {
        return CardStatusEnum.ofValue(getCardStatus())
                .orElseThrow(() -> new BadRequestException(INCORRECT_CARD_STATUS_VALUE));
    }
}
