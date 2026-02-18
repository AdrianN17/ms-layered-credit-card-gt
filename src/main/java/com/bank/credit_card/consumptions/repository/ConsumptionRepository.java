package com.bank.credit_card.consumptions.repository;

import com.bank.credit_card.consumptions.entity.ConsumptionEntity;
import com.bank.credit_card.generic.repository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ConsumptionRepository extends GenericRepository<ConsumptionEntity, Long> {

    @Query(value = "SELECT TOP 1 ca.debtTax FROM CardAccounts ca " +
            "JOIN Cards c ON ca.cardId = c.cardId " +
            "WHERE c.cardId = :cardId",
            nativeQuery = true)
    Optional<BigDecimal> getDebtTaxByConsumptionId(@Param("cardId") Long cardId);

    @Query(value = "SELECT TOP 1 ca.typeCard FROM Cards c " +
            "WHERE c.cardId = :cardId",
            nativeQuery = true)
    Optional<Short> getTypeCardByCardId(@Param("cardId") Long cardId);
}
