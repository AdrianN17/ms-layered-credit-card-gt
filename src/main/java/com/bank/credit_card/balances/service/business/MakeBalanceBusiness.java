package com.bank.credit_card.balances.service.business;

import com.bank.credit_card.balances.dto.request.BalanceRequestDto;

@FunctionalInterface
public interface MakeBalanceBusiness {
    BalanceRequestDto generateBalance(Long cardId);
}
