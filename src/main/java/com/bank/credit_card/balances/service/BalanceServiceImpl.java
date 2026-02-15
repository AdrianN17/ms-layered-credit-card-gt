package com.bank.credit_card.balances.service;

import com.bank.credit_card.balances.dto.BalanceDto;
import com.bank.credit_card.balances.mapper.BalanceMapper;
import com.bank.credit_card.balances.repository.BalanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;

    @Override
    public void createBalance(BalanceDto balanceDto) {

        /*balanceRepository.findLastBalanceByCard(balanceDto.cardId())
                .orElseThrow(() ->
                        new RuntimeException("Balance of this cardId not found"))
                .softDelete();

        balanceRepository.save(balanceMapper.toEntity(balanceDto));*/
    }
}
