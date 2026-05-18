package com.bank.credit_card.card.dto;

import com.bank.credit_card.card.enums.CategoryCardEnum;
import com.bank.credit_card.card.enums.TypeCardEnum;

public record CardDtoResponse(
        TypeCardEnum typeCard,
        CategoryCardEnum categoryCard,
        BenefitDtoResponse benefit,
        BalanceDtoResponse balance,
        CardAccountDtoResponse account
) {
}

