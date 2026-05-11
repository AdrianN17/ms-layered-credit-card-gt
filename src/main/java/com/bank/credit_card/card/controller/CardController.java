package com.bank.credit_card.card.controller;

import com.bank.credit_card.card.delegate.CardDelegate;
import com.bank.credit_card.card.schema.request.InitiateCardRequest;
import com.bank.credit_card.card.schema.response.RetrieveBalance200Response;
import com.bank.credit_card.generic.schema.response.Long202Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CardController implements CardApi {

    private final CardDelegate delegate;

    @Override
    public ResponseEntity<Long202Response> initiateCard(InitiateCardRequest initiateCardRequest,
                                                        BindingResult bindingResult) {
        return delegate.initiateCard(initiateCardRequest, bindingResult);
    }

    @Override
    public ResponseEntity<Long202Response> controlCard(Long cardId) {
        return delegate.controlCard(cardId);
    }

    @Override
    public ResponseEntity<RetrieveBalance200Response> retrieveBalance(Long cardId) {
        return delegate.retrieveBalance(cardId);
    }
}

