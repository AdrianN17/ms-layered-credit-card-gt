package com.bank.credit_card.currency.dto;

import java.math.BigDecimal;

public record CurrencyDto(BigDecimal value, String currency) {
}
