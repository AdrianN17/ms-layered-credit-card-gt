package com.bank.credit_card.balance.service;

import com.bank.credit_card.balance.dto.BalanceDtoRequest;
import com.bank.credit_card.balance.enums.BalanceUseCaseEnum;
import com.bank.credit_card.generic.service.GenericService;

import java.math.BigDecimal;

public interface BalanceService extends GenericService<BalanceDtoRequest, Long> {

    void apply(Long cardId, BigDecimal amount, BalanceUseCaseEnum type);

    void cancel(Long cardId, BigDecimal amount, BalanceUseCaseEnum type);
}
