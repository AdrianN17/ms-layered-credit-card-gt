package com.bank.credit_card.consumption.dto;

import com.bank.credit_card.generic.enums.CurrencyEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ConsumptionResponseDto(
        String sellerName,
        CurrencyEnum currency,
        BigDecimal amount,
        LocalDateTime consumptionDate,
        LocalDateTime consumptionApprobationDate
) {}

