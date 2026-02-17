package com.bank.credit_card.consumptions.service;

import com.bank.credit_card.consumptions.dto.request.ConsumptionRequestDto;
import com.bank.credit_card.generic.service.GenericService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ConsumptionService extends GenericService<ConsumptionRequestDto, Long> {
    void approbate(LocalDate targetDate,
                   LocalDateTime approbationDate);
}
