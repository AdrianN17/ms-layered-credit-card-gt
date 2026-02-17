package com.bank.credit_card.clients.dto.request;

import com.bank.credit_card.clients.commons.CategoryCard;
import com.bank.credit_card.clients.commons.TypeCard;

public record CardRequestDto(
        Long clientId,
        TypeCard typeCard,
        CategoryCard categoryCard
) {
}
