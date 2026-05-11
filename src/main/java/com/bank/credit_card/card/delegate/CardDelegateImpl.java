package com.bank.credit_card.card.delegate;

import com.bank.credit_card.card.schema.request.InitiateCardRequest;
import com.bank.credit_card.card.schema.response.RetrieveBalance200Response;
import com.bank.credit_card.generic.schema.response.Long202Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@AllArgsConstructor
public class CardDelegateImpl implements CardDelegate {

    @Override
    public ResponseEntity<Long202Response> initiateCard(InitiateCardRequest initiateCardRequest,
                                                         BindingResult bindingResult) {
        return null;
    }

    @Override
    public ResponseEntity<Long202Response> controlCard(Long cardId) {
        return null;
    }

    @Override
    public ResponseEntity<RetrieveBalance200Response> retrieveBalance(Long cardId) {
        return null;
    }
}

