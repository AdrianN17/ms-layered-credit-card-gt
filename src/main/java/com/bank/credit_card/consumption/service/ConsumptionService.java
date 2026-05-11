package com.bank.credit_card.consumption.service;

import com.bank.credit_card.consumption.dto.ConsumptionRequestDto;
import com.bank.credit_card.consumption.dto.ConsumptionResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ConsumptionService {

    void save(ConsumptionRequestDto request, String cardId);

    List<ConsumptionResponseDto> findAll(String cardId, LocalDateTime start, LocalDateTime end);

    List<UUID> split(Integer quantity, String cardId, UUID consumptionId);

    void delete(UUID id);
}
