package com.bank.credit_card.benefit.dto;

import java.math.BigDecimal;

public record BenefitRequestDto(
        Long benefitId,
        Integer pointEarned,
        Boolean hasDiscount,
        BigDecimal multiplierPoints,
        Long cardId
) {
}
