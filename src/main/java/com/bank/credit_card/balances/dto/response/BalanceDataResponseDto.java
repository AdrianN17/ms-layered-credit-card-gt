package com.bank.credit_card.balances.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BalanceDataResponseDto(
        String origin,

        BigDecimal amount,

        LocalDateTime date,

        String typeTransaccion,

        String currency
) {
}
