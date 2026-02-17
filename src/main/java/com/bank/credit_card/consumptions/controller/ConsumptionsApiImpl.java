package com.bank.credit_card.consumptions.controller;

import com.bank.credit_card.consumptions.delegate.ConsumptionsApiDelegate;
import com.bank.credit_card.consumptions.schema.request.ConsumptionRequest;
import com.bank.credit_card.consumptions.schema.request.SplitConsumptionDebtRequest;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConsumptionsApiImpl implements ConsumptionsApi {

    private final ConsumptionsApiDelegate consumptionsApiDelegate;

    @Override
    public ResponseEntity<Tracking> closeConsumption(Long consumptionId) {
        return consumptionsApiDelegate.closeConsumption(consumptionId);
    }

    @Override
    public ResponseEntity<Tracking> splitConsumptionDebt(Long consumptionId, SplitConsumptionDebtRequest splitConsumptionDebtRequest, BindingResult bindingResult) {
        return consumptionsApiDelegate.splitConsumptionDebt(consumptionId, splitConsumptionDebtRequest, bindingResult);
    }

    @Override
    public ResponseEntity<Tracking> createConsumption(Long cardId, ConsumptionRequest consumptionRequest, BindingResult bindingResult) {
        return consumptionsApiDelegate.createConsumption(cardId, consumptionRequest, bindingResult);
    }
}
