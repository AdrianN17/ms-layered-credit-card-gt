package com.bank.credit_card.consumption.controller;

import com.bank.credit_card.consumption.delegate.ConsumptionDelegate;
import com.bank.credit_card.consumption.schema.request.ExchangeConsumptionRequest;
import com.bank.credit_card.consumption.schema.request.InitiateConsumptionRequest;
import com.bank.credit_card.consumption.schema.response.RetrieveConsumption200Response;
import com.bank.credit_card.generic.schema.response.UUID202Response;
import com.bank.credit_card.generic.schema.response.UUIDList202Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ConsumptionController implements ConsumptionApi {

    private final ConsumptionDelegate delegate;

    @Override
    public ResponseEntity<UUID202Response> initiateConsumption(Long cardId,
                                                                InitiateConsumptionRequest initiateConsumptionRequest,
                                                                BindingResult bindingResult) {
        return delegate.initiateConsumption(cardId, initiateConsumptionRequest, bindingResult);
    }

    @Override
    public ResponseEntity<UUID202Response> controlConsumption(Long cardId, UUID consumptionId) {
        return delegate.controlConsumption(cardId, consumptionId);
    }

    @Override
    public ResponseEntity<UUIDList202Response> exchangeConsumption(Long cardId,
                                                                    UUID consumptionId,
                                                                    ExchangeConsumptionRequest exchangeConsumptionRequest,
                                                                    BindingResult bindingResult) {
        return delegate.exchangeConsumption(cardId, consumptionId, exchangeConsumptionRequest, bindingResult);
    }

    @Override
    public ResponseEntity<RetrieveConsumption200Response> retrieveConsumption(Long cardId,
                                                                               LocalDate dateStart,
                                                                               LocalDate dateEnd) {
        return delegate.retrieveConsumption(cardId, dateStart, dateEnd);
    }
}

