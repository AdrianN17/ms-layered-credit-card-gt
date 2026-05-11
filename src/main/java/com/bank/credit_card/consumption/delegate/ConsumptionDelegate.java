package com.bank.credit_card.consumption.delegate;

import com.bank.credit_card.consumption.schema.request.ExchangeConsumptionRequest;
import com.bank.credit_card.consumption.schema.request.InitiateConsumptionRequest;
import com.bank.credit_card.consumption.schema.response.RetrieveConsumption200Response;
import com.bank.credit_card.generic.schema.response.UUID202Response;
import com.bank.credit_card.generic.schema.response.UUIDList202Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.UUID;

public interface ConsumptionDelegate {

    ResponseEntity<UUID202Response> initiateConsumption(Long cardId,
                                                         InitiateConsumptionRequest initiateConsumptionRequest,
                                                         BindingResult bindingResult);

    ResponseEntity<UUID202Response> controlConsumption(Long cardId, UUID consumptionId);

    ResponseEntity<UUIDList202Response> exchangeConsumption(Long cardId,
                                                             UUID consumptionId,
                                                             ExchangeConsumptionRequest exchangeConsumptionRequest,
                                                             BindingResult bindingResult);

    ResponseEntity<RetrieveConsumption200Response> retrieveConsumption(Long cardId,
                                                                        LocalDate dateStart,
                                                                        LocalDate dateEnd);
}

