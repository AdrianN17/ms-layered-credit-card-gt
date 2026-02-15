package com.bank.credit_card.consumptions.service;

import com.bank.credit_card.consumptions.dto.ConsumptionDto;
import com.bank.credit_card.consumptions.dto.SplitConsumptionDebtDto;

public interface ConsumptionService {
    void createConsumption(ConsumptionDto consumptionDto);

    void closeConsumption(Long consumptionId);

    void splitConsumption(Long consumptionId, SplitConsumptionDebtDto splitConsumptionDebtDto);
}
