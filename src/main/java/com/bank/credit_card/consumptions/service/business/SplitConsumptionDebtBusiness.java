package com.bank.credit_card.consumptions.service.business;

import com.bank.credit_card.consumptions.dto.request.SplitConsumptionDebtRequestDto;

@FunctionalInterface
public interface SplitConsumptionDebtBusiness {
    void split(Long consumptionId, SplitConsumptionDebtRequestDto splitConsumptionDebtRequestDto);
}
