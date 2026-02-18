package com.bank.credit_card.clients.delegate;

import com.bank.credit_card.clients.schema.request.CreateCardRequest;
import com.bank.credit_card.generic.schema.response.Tracking;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface CardsApiDelegate {
    ResponseEntity<Tracking> cloneCard(Long cardId);

    ResponseEntity<Tracking> closeCard(Long cardId);

    ResponseEntity<Tracking> createCard(Long clientId, CreateCardRequest createCardRequest, BindingResult bindingResult);
}
