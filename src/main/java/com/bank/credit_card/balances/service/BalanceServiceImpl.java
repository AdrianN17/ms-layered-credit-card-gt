package com.bank.credit_card.balances.service;

import com.bank.credit_card.balances.dto.BalanceDto;
import com.bank.credit_card.balances.mapper.BalanceMapper;
import com.bank.credit_card.balances.repository.BalanceRepository;
import com.bank.credit_card.generic.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BalanceServiceImpl extends GenericServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;

    @Override
    public Long create(BalanceDto balanceDto) {
        balanceRepository.save(balanceMapper.toEntity(balanceDto));
        return  null;
    }

    @Override
    public Long close(Long cardId) {
        balanceRepository.findLastBalanceByCard(cardId)
                .orElseThrow(() ->
                        new RuntimeException("Balance of this cardId not found"))
                .softDelete();
        return  null;
    }


}
