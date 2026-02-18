package com.bank.credit_card.clients.repository;

import com.bank.credit_card.clients.entity.CardAccountEntity;
import com.bank.credit_card.generic.repository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardAccountRepository extends GenericRepository<CardAccountEntity, Long> {

    @Query(value = "SELECT TOP 1 ca.cardAccountId FROM CardAccounts ca WHERE ca.cardId = :cardId AND ca.status = 1",
            nativeQuery = true)
    Optional<Long> findCardAccountIdByCardId(@Param("cardId") Long cardId);


    @Query(value = "SELECT CASE WHEN EXISTS (" +
            "SELECT 1 FROM CardAccounts ca JOIN Cards c2 ON ca.cardId = c2.cardId " +
            "WHERE c2.clientId = (SELECT clientId FROM Cards WHERE cardId = :cardId) " +
            "AND ca.cardStatus = 3 AND ca.status = 1) THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END",
            nativeQuery = true)
    Boolean existsInDebtCard(@Param("cardId") Long cardId);

}
