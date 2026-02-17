package com.bank.credit_card.consumptions.delegate;

import com.bank.credit_card.consumptions.schema.request.ConsumptionRequest;
import com.bank.credit_card.consumptions.schema.request.SplitConsumptionDebtRequest;
import com.bank.credit_card.generic.schema.response.Tracking;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ConsumptionsApiDelegate {
    ResponseEntity<Tracking> closeConsumption(Long consumptionId);

    ResponseEntity<Tracking> splitConsumptionDebt(Long consumptionId, SplitConsumptionDebtRequest splitConsumptionDebtRequest, BindingResult bindingResult);

    ResponseEntity<Tracking> createConsumption(Long cardId, ConsumptionRequest consumptionRequest, BindingResult bindingResult);

}
