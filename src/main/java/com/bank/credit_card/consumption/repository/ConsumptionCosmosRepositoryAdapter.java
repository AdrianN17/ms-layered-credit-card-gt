package com.bank.credit_card.consumption.repository;

import com.bank.credit_card.consumption.entity.ConsumptionEntity;
import com.bank.credit_card.consumption.entity.ConsumptionEntityCosmos;
import com.bank.credit_card.generic.enums.StatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.bank.credit_card.generic.constant.TimeConstant.*;

@Component
@Profile("new")
@AllArgsConstructor
public class ConsumptionCosmosRepositoryAdapter implements ConsumptionRepository {

    private final ConsumptionCosmosRepository repository;

    @Override
    public ConsumptionEntity save(ConsumptionEntity entity) {
        return repository.save((ConsumptionEntityCosmos) entity);
    }

    @Override
    public Optional<ConsumptionEntity> findById(UUID id) {
        return repository.findById(id).map(e -> (ConsumptionEntity) e);
    }

    @Override
    public List<ConsumptionEntity> findByCardIdAndConsumptionDateBetween(String cardId, LocalDate start, LocalDate end) {
        return repository.findByCardIdAndConsumptionDateBetween(cardId,
                        start.atStartOfDay(),
                        end.atTime(LAST_HOUR, LAST_MINUTE, LAST_SECOND))
                .stream()
                .map(e -> (ConsumptionEntity) e)
                .toList();
    }

    @Override
    public void softDelete(UUID id) {
        repository.findById(id).ifPresent(entity -> {
            entity.softDelete();
            repository.save(entity);
        });
    }
}

