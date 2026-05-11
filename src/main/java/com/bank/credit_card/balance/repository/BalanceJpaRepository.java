package com.bank.credit_card.balance.repository;

import com.bank.credit_card.balance.entity.BalanceEntity;
import com.bank.credit_card.generic.enums.StatusEnum;
import com.bank.credit_card.generic.repository.GenericJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceJpaRepository extends GenericJpaRepository<BalanceEntity, Long> {

    Optional<BalanceEntity> findByCardIdAndStatus(Long cardId, StatusEnum status);

    default Optional<BalanceEntity> findActiveByCardId(Long cardId) {
        return findByCardIdAndStatus(cardId, StatusEnum.ACTIVE);
    }
}
