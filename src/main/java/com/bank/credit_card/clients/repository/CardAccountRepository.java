package com.bank.credit_card.clients.repository;

import com.bank.credit_card.clients.entity.CardAccountEntity;
import com.bank.credit_card.generic.repository.GenericRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface CardAccountRepository extends GenericRepository<CardAccountEntity, Long> {

    @Query(value = "SELECT ca.cardAccountId FROM CardAccounts ca WHERE ca.cardId = :cardId AND ca.status = 1",
            nativeQuery = true)
    Optional<Long> findCardAccountIdByCardId(@Param("cardId") Long cardId);

}
