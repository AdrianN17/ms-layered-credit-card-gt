package com.bank.credit_card.consumption.repository;

import com.bank.credit_card.consumption.entity.ConsumptionEntity;
import com.bank.credit_card.consumption.entity.ConsumptionEntityMongo;
import com.bank.credit_card.generic.enums.StatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Profile("old")
@AllArgsConstructor
public class ConsumptionMongoRepositoryAdapter implements ConsumptionRepository {

    private final ConsumptionMongoRepository repository;

    @Override
    public ConsumptionEntity save(ConsumptionEntity entity) {
        return repository.save((ConsumptionEntityMongo) entity);
    }

    @Override
    public Optional<ConsumptionEntity> findById(UUID id) {
        return repository.findById(id).map(e -> (ConsumptionEntity) e);
    }

    @Override
    public List<ConsumptionEntity> findByCardIdAndConsumptionDateBetween(String cardId, LocalDateTime start, LocalDateTime end) {
        return repository.findByCardIdAndConsumptionDateBetween(cardId, start, end)
                .stream()
                .map(e -> (ConsumptionEntity) e)
                .toList();
    }

    @Override
    public void softDelete(UUID id) {
        repository.findById(id).ifPresent(entity -> {
            entity.setStatus(StatusEnum.INACTIVE);
            repository.save(entity);
        });
    }
}

