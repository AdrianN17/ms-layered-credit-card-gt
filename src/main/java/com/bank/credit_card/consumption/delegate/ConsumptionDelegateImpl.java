package com.bank.credit_card.consumption.delegate;

import com.bank.credit_card.consumption.schema.request.ExchangeConsumptionRequest;
import com.bank.credit_card.consumption.schema.request.InitiateConsumptionRequest;
import com.bank.credit_card.consumption.schema.response.RetrieveConsumption200Response;
import com.bank.credit_card.generic.schema.response.UUID202Response;
import com.bank.credit_card.generic.schema.response.UUIDList202Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ConsumptionDelegateImpl implements ConsumptionDelegate {

    @Override
    public ResponseEntity<UUID202Response> initiateConsumption(Long cardId,
                                                                InitiateConsumptionRequest initiateConsumptionRequest,
                                                                BindingResult bindingResult) {
        return null;
    }

    @Override
    public ResponseEntity<UUID202Response> controlConsumption(Long cardId, UUID consumptionId) {
        return null;
    }

    @Override
    public ResponseEntity<UUIDList202Response> exchangeConsumption(Long cardId,
                                                                    UUID consumptionId,
                                                                    ExchangeConsumptionRequest exchangeConsumptionRequest,
                                                                    BindingResult bindingResult) {
        return null;
    }

    @Override
    public ResponseEntity<RetrieveConsumption200Response> retrieveConsumption(Long cardId,
                                                                               LocalDate dateStart,
                                                                               LocalDate dateEnd) {
        return null;
    }
}

