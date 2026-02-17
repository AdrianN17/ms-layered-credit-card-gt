package com.bank.credit_card.clients.repository.procedure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class CloneCardAccountRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public Long cloneCardAccount(
            Long cardId) {

        StoredProcedureQuery query =
                entityManager.createNamedStoredProcedureQuery("usp_clone_cardaccount");

        query.setParameter("sourceCardAccountId", cardId);

        query.execute();

        return (Long) query.getOutputParameterValue("newCardAccountId");
    }
}
