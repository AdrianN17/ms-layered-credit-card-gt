package com.bank.credit_card.card.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BalanceDtoResponse(
        BigDecimal totalAmount,
        BigDecimal oldAmount,
        BigDecimal availableAmount,
        LocalDate startDate,
        LocalDate endDate
) {}

