package com.bank.credit_card.clients.controller;

import com.bank.credit_card.clients.delegate.CardsApiDelegate;
import com.bank.credit_card.clients.schema.request.CreateCardRequest;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CardsApiImpl implements CardsApi {

    private final CardsApiDelegate cardsApiDelegate;

    @Override
    public ResponseEntity<Tracking> cloneCard(Long cardId) {
        return cardsApiDelegate.cloneCard(cardId);
    }

    @Override
    public ResponseEntity<Tracking> closeCard(Long cardId) {
        return cardsApiDelegate.closeCard(cardId);
    }

    @Override
    public ResponseEntity<Tracking> createCard(Long clientId, CreateCardRequest createCardRequest, BindingResult bindingResult) {
        return cardsApiDelegate.createCard(clientId, createCardRequest, bindingResult);
    }
}
