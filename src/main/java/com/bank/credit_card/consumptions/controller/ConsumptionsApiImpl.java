package com.bank.credit_card.consumptions.controller;

import com.bank.credit_card.consumptions.mapper.ConsumptionMapper;
import com.bank.credit_card.consumptions.mapper.SplitConsumptionDebtMapper;
import com.bank.credit_card.consumptions.schema.request.ConsumptionRequest;
import com.bank.credit_card.consumptions.schema.request.SplitConsumptionDebtRequest;
import com.bank.credit_card.consumptions.service.ConsumptionService;
import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConsumptionsApiImpl implements ConsumptionsApi{

    private final ConsumptionService consumptionService;
    private final SplitConsumptionDebtMapper splitConsumptionDebtMapper;
    private final ConsumptionMapper consumptionMapper;

    @Override
    public ResponseEntity<Tracking> closeConsumption(Long consumptionId) {
        consumptionService.closeConsumption(consumptionId);
        return null;
    }

    @Override
    public ResponseEntity<Tracking> splitConsumptionDebt(Long consumptionId, SplitConsumptionDebtRequest splitConsumptionDebtRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new CustomBadRequest(bindingResult);
        consumptionService.splitConsumption(consumptionId, splitConsumptionDebtMapper.toDto(splitConsumptionDebtRequest));
        return null;
    }

    @Override
    public ResponseEntity<Tracking> createConsumption(Long cardId, ConsumptionRequest consumptionRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new CustomBadRequest(bindingResult);
        consumptionService.createConsumption(consumptionMapper.toDto(consumptionRequest, cardId));
        return null;
    }
}
