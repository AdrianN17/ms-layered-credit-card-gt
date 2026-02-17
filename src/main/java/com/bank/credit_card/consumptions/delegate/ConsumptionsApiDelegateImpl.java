package com.bank.credit_card.consumptions.delegate;

import com.bank.credit_card.consumptions.mapper.ConsumptionMapper;
import com.bank.credit_card.consumptions.mapper.SplitConsumptionDebtMapper;
import com.bank.credit_card.consumptions.schema.request.ConsumptionRequest;
import com.bank.credit_card.consumptions.schema.request.SplitConsumptionDebtRequest;
import com.bank.credit_card.consumptions.service.ConsumptionService;
import com.bank.credit_card.consumptions.service.SplitConsumptionDebtService;
import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@AllArgsConstructor
public class ConsumptionsApiDelegateImpl implements ConsumptionsApiDelegate{

    private final ConsumptionService consumptionService;
    private final SplitConsumptionDebtMapper splitConsumptionDebtMapper;
    private final ConsumptionMapper consumptionMapper;
    private final SplitConsumptionDebtService splitConsumptionDebtService;

    @Override
    public ResponseEntity<Tracking> closeConsumption(Long consumptionId) {
        consumptionService.close(consumptionId);
        return null;
    }

    @Override
    public ResponseEntity<Tracking> splitConsumptionDebt(Long consumptionId, SplitConsumptionDebtRequest splitConsumptionDebtRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new CustomBadRequest(bindingResult);
        splitConsumptionDebtService.split(consumptionId, splitConsumptionDebtMapper.toDto(splitConsumptionDebtRequest));
        return null;
    }

    @Override
    public ResponseEntity<Tracking> createConsumption(Long cardId, ConsumptionRequest consumptionRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new CustomBadRequest(bindingResult);
        consumptionService.create(consumptionMapper.toDto(consumptionRequest, cardId));
        return null;
    }
}
