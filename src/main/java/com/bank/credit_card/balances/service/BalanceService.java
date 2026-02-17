package com.bank.credit_card.balances.service;

import com.bank.credit_card.balances.dto.request.BalanceRequestDto;
import com.bank.credit_card.balances.dto.response.BalanceResponseDto;
import com.bank.credit_card.generic.service.GenericService;

import java.util.Optional;

public interface BalanceService extends GenericService<BalanceRequestDto, Long> {
    BalanceRequestDto generateBalanceByCardId(Long cardId);
    Optional<Long> getIdOptional(Long cardId);
    Long getId(Long cardId);
    BalanceResponseDto getBalance(Long balanceId);
}
