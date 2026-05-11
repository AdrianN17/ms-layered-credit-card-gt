package com.bank.credit_card.balance.service;

import com.bank.credit_card.balance.dto.BalanceDtoRequest;
import com.bank.credit_card.balance.entity.BalanceEntity;
import com.bank.credit_card.balance.enums.BalanceUseCaseEnum;
import com.bank.credit_card.balance.exception.BalancePersistanceException;
import com.bank.credit_card.balance.mapper.BalanceMapper;
import com.bank.credit_card.balance.repository.BalanceJpaRepository;
import com.bank.credit_card.balance.usecase.BalanceUseCaseFactory;
import com.bank.credit_card.generic.enums.StatusEnum;
import com.bank.credit_card.generic.model.Amount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.bank.credit_card.balance.constant.BalanceConstant.OVERCHARGE_LIMIT;
import static com.bank.credit_card.balance.exception.BalanceErrorMessage.*;

@Service
@AllArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceJpaRepository balanceJpaRepository;
    private final BalanceMapper balanceMapper;
    private final BalanceUseCaseFactory balanceUseCaseFactory;

    @Override
    public Long save(BalanceDtoRequest request, Long cardId) {
        BalanceEntity entity = balanceMapper.toEntity(request, cardId);
        return balanceJpaRepository.save(entity).getIdBalance();
    }

    @Override
    public void delete(Long cardId) {
        var entity = findActiveOrThrow(cardId);
        entity.setStatus(StatusEnum.INACTIVE);
        balanceJpaRepository.save(entity);
    }


    //pasarlo a trigger / procedure
    @Override
    public Boolean isOvercharged(Long cardId) {
        var entity = findActiveOrThrow(cardId);
        BigDecimal limitOvercharge = entity.getTotalAmount().multiply(OVERCHARGE_LIMIT);
        BigDecimal totalLimit = entity.getTotalAmount().add(limitOvercharge);
        return entity.getAvailableAmount().compareTo(totalLimit) > 0;
    }

    @Override
    public void apply(Long cardId, BigDecimal amount, BalanceUseCaseEnum type) {
        var entity = findActiveOrThrow(cardId);
        balanceUseCaseFactory.create(type, entity).apply(amount);
    }

    @Override
    public void cancel(Long cardId, BigDecimal amount, BalanceUseCaseEnum type) {
        var entity = findActiveOrThrow(cardId);
        balanceUseCaseFactory.create(type, entity).cancel(amount);
    }

    private BalanceEntity findActiveOrThrow(Long cardId) {
        return balanceJpaRepository.findActiveByCardId(cardId)
                .orElseThrow(() -> new BalancePersistanceException(BALANCE_NOT_FOUND));
    }
}
