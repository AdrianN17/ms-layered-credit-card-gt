package com.bank.credit_card.clients.repository.procedure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import static com.bank.credit_card.clients.util.CardGeneratorUtility.*;

@Repository
public class CloneCardRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public Long cloneCard(
            Long cardId) {

        StoredProcedureQuery query =
                entityManager.createNamedStoredProcedureQuery("usp_clone_card");

        query.setParameter("sourceCardId", cardId);
        query.setParameter("numberCard", generateCardNumber());
        query.setParameter("cvv", generateCVV());
        query.setParameter("dateCard", generateExpirationDate());

        query.execute();

        return (Long) query.getOutputParameterValue("newCardId");
    }
}
