package com.bank.credit_card.consumptions.service;

import com.bank.credit_card.consumptions.dto.request.SplitConsumptionDebtRequestDto;

@FunctionalInterface
public interface SplitConsumptionDebtService {
    void split(Long consumptionId, SplitConsumptionDebtRequestDto splitConsumptionDebtRequestDto);
}
