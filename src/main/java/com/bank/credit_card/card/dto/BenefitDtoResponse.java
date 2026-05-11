package com.bank.credit_card.card.dto;

import java.math.BigDecimal;

public record BenefitDtoResponse(
        Boolean hasDiscount,
        BigDecimal multiplierPoints,
        Integer totalPoints
) {}

