package com.bank.credit_card.clients.repository;

import com.bank.credit_card.clients.entity.CardEntity;
import com.bank.credit_card.generic.repository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends GenericRepository<CardEntity, Long> {

    @Query("SELECT COUNT(c) FROM CardEntity c WHERE c.clientId = :clientId")
    Integer countByClientId(Long clientId);

    @Query("SELECT COUNT(c) FROM CardEntity c WHERE c.clientId = :clientId AND c.typeCard = :typeCard AND c.categoryCard = :categoryCard")
    Integer countByClientId(Long clientId, Integer typeCard, Integer categoryCard);

    @Query("SELECT (COUNT(ca) > 0) FROM CardAccountEntity ca JOIN CardEntity c ON ca.cardId = c.cardId " +
            "WHERE c.clientId = (SELECT c2.clientId FROM CardEntity c2 WHERE c2.cardId = :cardId) " +
            "AND ca.cardStatus = 2 AND ca.status = 1")
    Boolean existsActiveCardOvercharge(@Param("cardId") Long cardId);
}
