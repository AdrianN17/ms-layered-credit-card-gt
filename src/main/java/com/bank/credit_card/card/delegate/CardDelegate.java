package com.bank.credit_card.card.delegate;

import com.bank.credit_card.card.schema.request.InitiateCardRequest;
import com.bank.credit_card.card.schema.response.RetrieveBalance200Response;
import com.bank.credit_card.generic.schema.response.Long202Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface CardDelegate {

    ResponseEntity<Long202Response> initiateCard(InitiateCardRequest initiateCardRequest,
                                                  BindingResult bindingResult);

    ResponseEntity<Long202Response> controlCard(Long cardId);

    ResponseEntity<RetrieveBalance200Response> retrieveBalance(Long cardId);
}

