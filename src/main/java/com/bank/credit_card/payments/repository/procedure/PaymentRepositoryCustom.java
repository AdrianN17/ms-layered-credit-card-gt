package com.bank.credit_card.payments.repository.procedure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public class PaymentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public Integer approbate(
            LocalDate targetDate,
            LocalDateTime approbationDate) {

        var query = entityManager.createNamedStoredProcedureQuery("usp_update_payments_approbation_date");

        query.setParameter("targetDate", targetDate);
        query.setParameter("approbationDate", approbationDate);

        query.execute();

        return (Integer) query.getOutputParameterValue("updatedCount");
    }
}
