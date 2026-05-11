package com.bank.credit_card.benefit.repository;

import com.bank.credit_card.benefit.entity.BenefitEntity;
import com.bank.credit_card.generic.enums.StatusEnum;
import com.bank.credit_card.generic.repository.GenericJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BenefitJpaRepository extends GenericJpaRepository<BenefitEntity, Long> {

    Optional<BenefitEntity> findByCardIdAndStatus(Long cardId, StatusEnum status);

    default Optional<BenefitEntity> findActiveByCardId(Long cardId) {
        return findByCardIdAndStatus(cardId, StatusEnum.ACTIVE);
    }
}
