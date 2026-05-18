package com.bank.credit_card.consumption.service;

import com.bank.credit_card.consumption.dto.ConsumptionRequestDto;
import com.bank.credit_card.consumption.dto.ConsumptionResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ConsumptionService {

    void save(ConsumptionRequestDto request);

    List<ConsumptionResponseDto> findAll(String cardId, LocalDate start, LocalDate end);

    List<ConsumptionRequestDto> split(Integer quantity, String cardId, UUID consumptionId);

    void delete(UUID id);

    ConsumptionResponseDto get(UUID id);
}
