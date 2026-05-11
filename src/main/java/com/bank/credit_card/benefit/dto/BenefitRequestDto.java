package com.bank.credit_card.benefit.dto;

import java.math.BigDecimal;

public record BenefitRequestDto(
        Integer pointEarned,
        Boolean hasDiscount,
        BigDecimal multiplierPoints,
        Long cardId
) {
}
