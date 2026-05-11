package com.bank.credit_card.card.dto;

public record CardDtoResponse(
        String typeCard,
        String categoryCard,
        BenefitDtoResponse benefit,
        BalanceDtoResponse balance,
        CardAccountDtoResponse account
) {
}

