package com.bank.credit_card.balance.service;

import com.bank.credit_card.balance.dto.BalanceDtoRequest;
import com.bank.credit_card.balance.entity.BalanceEntity;
import com.bank.credit_card.balance.enums.BalanceUseCaseEnum;
import com.bank.credit_card.balance.mapper.BalanceMapper;
import com.bank.credit_card.balance.repository.BalanceJpaRepository;
import com.bank.credit_card.balance.usecase.BalanceUseCaseFactory;
import com.bank.credit_card.generic.enums.StatusEnum;
import com.bank.credit_card.generic.exception.InternalServerErrorException;
import com.bank.credit_card.generic.exception.UnprocessableEntityException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.bank.credit_card.balance.constant.BalanceConstant.OVERCHARGE_LIMIT;
import static com.bank.credit_card.balance.exception.BalanceErrorMessage.BALANCE_NOT_FOUND;
import static com.bank.credit_card.balance.exception.BalanceErrorMessage.CARD_STATUS_UPDATE_FAILED;

@Service
@AllArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceJpaRepository balanceJpaRepository;
    private final BalanceMapper balanceMapper;
    private final BalanceUseCaseFactory balanceUseCaseFactory;

    @Override
    public Long save(BalanceDtoRequest request) {
        BalanceEntity entity = balanceMapper.toEntity(request);
        return balanceJpaRepository.save(entity)
                .getIdBalance();
    }

    @Override
    public void delete(Long cardId) {
        var entity = findActiveOrThrow(cardId);
        entity.softDelete();
        balanceJpaRepository.save(entity);
    }

    public void updateCardStatus(Long cardId) {
        try {
            balanceJpaRepository.callUpdateCardStatus(cardId, OVERCHARGE_LIMIT);
        } catch (Exception e) {
            throw new InternalServerErrorException(String.format(CARD_STATUS_UPDATE_FAILED, cardId), e);
        }
    }

    @Override
    public void apply(Long cardId, BigDecimal amount, BalanceUseCaseEnum type) {
        var entity = findActiveOrThrow(cardId);
        balanceUseCaseFactory.create(type, entity).apply(amount);
        updateCardStatus(cardId);
    }

    @Override
    public void cancel(Long cardId, BigDecimal amount, BalanceUseCaseEnum type) {
        var entity = findActiveOrThrow(cardId);
        balanceUseCaseFactory.create(type, entity).cancel(amount);
        updateCardStatus(cardId);
    }

    private BalanceEntity findActiveOrThrow(Long cardId) {
        return balanceJpaRepository.findActiveByCardId(cardId)
                .orElseThrow(() -> new UnprocessableEntityException(BALANCE_NOT_FOUND));
    }
}
