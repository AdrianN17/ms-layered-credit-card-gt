package com.bank.credit_card.balances.repository;

import com.bank.credit_card.balances.entity.BalanceEntity;
import com.bank.credit_card.generic.repository.GenericRepository;
import com.bank.credit_card.generic.commons.Status;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends GenericRepository<BalanceEntity, Long> {

    Optional<BalanceEntity> findFirstByCardIdAndStatusOrderByStartDateDesc(Long cardId, Status status);

    default Optional<BalanceEntity> findLastBalanceByCard(Long cardId) {
        return findFirstByCardIdAndStatusOrderByStartDateDesc(cardId, Status.ACTIVE);
    }
}
