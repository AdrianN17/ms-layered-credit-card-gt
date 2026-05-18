package com.bank.credit_card.consumption.service;

import com.bank.credit_card.consumption.dto.ConsumptionRequestDto;
import com.bank.credit_card.consumption.dto.ConsumptionResponseDto;
import com.bank.credit_card.consumption.entity.ConsumptionEntity;
import com.bank.credit_card.consumption.mapper.ConsumptionMapper;
import com.bank.credit_card.consumption.repository.ConsumptionRepository;
import com.bank.credit_card.generic.exception.BadRequestException;
import com.bank.credit_card.generic.exception.UnprocessableEntityException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.bank.credit_card.consumption.exception.ConsumptionErrorMessage.*;
import static com.bank.credit_card.generic.util.Validation.isNotConditional;
import static com.bank.credit_card.generic.util.Validation.isNotNull;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ConsumptionServiceImpl implements ConsumptionService {

    private final ConsumptionRepository consumptionRepository;
    private final ConsumptionMapper consumptionMapper;

    @Override
    public void save(ConsumptionRequestDto request) {
        ConsumptionEntity entity = consumptionMapper.toEntity(request);
        consumptionRepository.save(entity);
    }

    @Override
    public List<ConsumptionResponseDto> findAll(String cardId, LocalDate start, LocalDate end) {
        return consumptionRepository.findByCardIdAndConsumptionDateBetween(cardId, start, end)
                .stream()
                .map(consumptionMapper::toDto)
                .toList();
    }

    @Override
    public List<ConsumptionRequestDto> split(Integer quantity, String cardId, UUID consumptionId) {
        ConsumptionEntity entity = consumptionRepository.findById(consumptionId)
                .orElseThrow(() -> new UnprocessableEntityException(CONSUMPTION_NOT_FOUND));

        isNotConditional(isNull(entity.getConsumptionApprobationDate()),
                new UnprocessableEntityException(CONSUMPTION_IS_STILL_IN_APPROBATION));

        isNotNull(quantity, new BadRequestException(QUANTITY_CANNOT_BE_NULL));

        BigDecimal splitAmount = entity.getAmount()
                .divide(BigDecimal.valueOf(quantity), 2, RoundingMode.HALF_UP);

        return IntStream.rangeClosed(1, quantity).mapToObj(count -> {
            String splitSellerName = entity.getSellerName() + " " + CONSUMPTION_SPLIT + " " + count;
            LocalDateTime splitDate = entity.getConsumptionApprobationDate().plusMonths(count);

            return ConsumptionRequestDto.of(
                    splitSellerName,
                    entity.getCurrency(),
                    splitAmount,
                    Long.valueOf(cardId)
            );
        }).toList();
    }

    @Override
    public void delete(UUID id) {
        ConsumptionEntity entity = consumptionRepository.findById(id)
                .orElseThrow(() -> new UnprocessableEntityException(CONSUMPTION_NOT_FOUND));

        isNotConditional(isNull(entity.getConsumptionApprobationDate()),
                new UnprocessableEntityException(CONSUMPTION_IS_STILL_IN_APPROBATION));

        consumptionRepository.softDelete(id);
    }

    @Override
    public ConsumptionResponseDto get(UUID id) {
        ConsumptionEntity entity = consumptionRepository.findById(id)
                .orElseThrow(() -> new UnprocessableEntityException(CONSUMPTION_NOT_FOUND));

        return consumptionMapper.toDto(entity);
    }
}
