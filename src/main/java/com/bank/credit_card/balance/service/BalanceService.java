package com.bank.credit_card.balance.service;

import com.bank.credit_card.balance.dto.BalanceDtoRequest;
import com.bank.credit_card.balance.enums.BalanceUseCaseEnum;

import java.math.BigDecimal;

public interface BalanceService {
    Long save(BalanceDtoRequest request);

    void delete(Long cardId);

    void apply(Long cardId, BigDecimal amount, BalanceUseCaseEnum type);

    void cancel(Long cardId, BigDecimal amount, BalanceUseCaseEnum type);
}
