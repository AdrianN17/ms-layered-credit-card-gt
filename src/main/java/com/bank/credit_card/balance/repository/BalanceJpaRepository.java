package com.bank.credit_card.balance.repository;

import com.bank.credit_card.balance.entity.BalanceEntity;
import com.bank.credit_card.generic.enums.StatusEnum;
import com.bank.credit_card.generic.repository.GenericJpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface BalanceJpaRepository extends GenericJpaRepository<BalanceEntity, Long> {

    Optional<BalanceEntity> findByCardIdAndStatus(Long cardId, StatusEnum status);

    default Optional<BalanceEntity> findActiveByCardId(Long cardId) {
        return findByCardIdAndStatus(cardId, StatusEnum.ACTIVE);
    }

    @Procedure(name = "BalanceEntity.updateCardStatus")
    void callUpdateCardStatus(@Param("cardId") Long cardId,
                              @Param("overchargeLimit") BigDecimal overchargeLimit);
}
