package com.bank.credit_card.consumption.service;

import com.bank.credit_card.consumption.dto.ConsumptionRequestDto;
import com.bank.credit_card.consumption.dto.ConsumptionResponseDto;
import com.bank.credit_card.generic.service.GenericService;
import com.bank.credit_card.generic.service.GenericTransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public interface ConsumptionService extends GenericService<ConsumptionRequestDto, UUID>,
        GenericTransactionService<String, UUID, ConsumptionResponseDto> {

    List<ConsumptionRequestDto> split(Integer quantity, String cardId, UUID consumptionId, BigDecimal debTax);
}
