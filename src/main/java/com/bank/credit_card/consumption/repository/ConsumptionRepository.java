package com.bank.credit_card.consumption.repository;

import com.bank.credit_card.consumption.entity.ConsumptionEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConsumptionRepository {

    ConsumptionEntity save(ConsumptionEntity entity);

    Optional<ConsumptionEntity> findById(UUID id);

    List<ConsumptionEntity> findByCardIdAndConsumptionDateBetween(String cardId, LocalDate start, LocalDate end);

    void softDelete(UUID id);
}

