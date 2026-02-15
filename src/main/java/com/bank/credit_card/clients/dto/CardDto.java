package com.bank.credit_card.clients.dto;

import com.bank.credit_card.clients.commons.CategoryCard;
import com.bank.credit_card.clients.commons.TypeCard;

public record CardDto(
        Integer cardId,
        TypeCard typeCard,
        String numberCard,
        CategoryCard categoryCard,
        String cvv,
        CardAccountDto cardAccountDto
) {
}
