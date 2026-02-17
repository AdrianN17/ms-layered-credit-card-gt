package com.bank.credit_card.clients.dto.response;

import com.bank.credit_card.clients.commons.CategoryCard;
import com.bank.credit_card.clients.commons.TypeCard;

public record CardResponseDto(
        Long clientId,
        TypeCard typeCard,
        CategoryCard categoryCard,
        String numberCard,
        CardAccountResponseDto cardAccountResponseDto
) {
}
