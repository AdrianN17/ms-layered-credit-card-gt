package com.bank.credit_card.balance.usecase;

import java.math.BigDecimal;

public interface BalanceUseCase {
    void apply(BigDecimal amount);

    void cancel(BigDecimal amount);
}

