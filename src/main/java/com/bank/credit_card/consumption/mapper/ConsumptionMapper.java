package com.bank.credit_card.consumption.mapper;

import com.bank.credit_card.consumption.dto.ConsumptionRequestDto;
import com.bank.credit_card.consumption.dto.ConsumptionResponseDto;
import com.bank.credit_card.consumption.entity.ConsumptionEntity;
import com.bank.credit_card.consumption.entity.ConsumptionEntityCosmos;
import com.bank.credit_card.consumption.entity.ConsumptionEntityMongo;
import com.bank.credit_card.consumption.schema.request.ConsumptionRequest;
import com.bank.credit_card.consumption.schema.response.ConsumptionResponse;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.generic.enums.StatusEnum;
import com.bank.credit_card.generic.mapper.EntityMapper;
import com.bank.credit_card.generic.mapper.RequestDtoMapper;
import com.bank.credit_card.generic.mapper.ResponseDtoMapper;
import com.bank.credit_card.generic.mapper.ResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Objects;

import static com.bank.credit_card.consumption.exception.ConsumptionErrorMessage.CONSUMPTION_APPROBATION_DATE_NOT_NULL;
import static com.bank.credit_card.consumption.exception.ConsumptionErrorMessage.CONSUMPTION_DATE_NOT_NULL;

@Component
@AllArgsConstructor
public class ConsumptionMapper implements
        ResponseDtoMapper<ConsumptionResponseDto, ConsumptionEntity>,
        EntityMapper<ConsumptionRequestDto, ConsumptionEntity>,
        RequestDtoMapper<ConsumptionRequest, ConsumptionRequestDto>,
        ResponseMapper<ConsumptionResponseDto, ConsumptionResponse> {

    private final Environment environment;

    @Override
    public ConsumptionRequestDto toRequestDto(ConsumptionRequest request, Long cardId) {
        return ConsumptionRequestDto.of(
                request.getSellerName(),
                CurrencyEnum.ofCode(request.getCurrency()),
                request.getAmount(),
                cardId
        );
    }

    @Override
    public ConsumptionEntity toEntity(ConsumptionRequestDto dto) {
        boolean isNew = Arrays.asList(environment.getActiveProfiles()).contains("new");

        if (isNew) {
            return ConsumptionEntityCosmos.builder()
                    .consumptionId(dto.consumptionId())
                    .cardId(dto.cardId().toString())
                    .sellerName(dto.sellerName())
                    .currency(dto.currency())
                    .amount(dto.amount())
                    .consumptionDate(LocalDateTime.now())
                    .consumptionApprobationDate(LocalDateTime.now())
                    .status(StatusEnum.ACTIVE)
                    .createdDate(LocalDateTime.now())
                    .build();
        } else {
            return ConsumptionEntityMongo.builder()
                    .consumptionId(dto.consumptionId())
                    .cardId(dto.cardId().toString())
                    .sellerName(dto.sellerName())
                    .currency(dto.currency())
                    .amount(dto.amount())
                    .consumptionDate(LocalDateTime.now())
                    .consumptionApprobationDate(LocalDateTime.now())
                    .status(StatusEnum.ACTIVE)
                    .createdDate(LocalDateTime.now())
                    .build();
        }
    }


    public ConsumptionResponseDto toResponseDto(ConsumptionEntity entity) {
        return new ConsumptionResponseDto(
                entity.getSellerName(),
                entity.getCurrency(),
                entity.getAmount(),
                entity.getConsumptionDate(),
                entity.getConsumptionApprobationDate()
        );
    }

    @Override
    public ConsumptionResponse toResponse(ConsumptionResponseDto dto) {
        return new ConsumptionResponse(
                dto.sellerName(),
                dto.currency().name(),
                dto.amount(),
                Objects.requireNonNull(dto.consumptionDate(),            CONSUMPTION_DATE_NOT_NULL).atOffset(ZoneOffset.UTC),
                Objects.requireNonNull(dto.consumptionApprobationDate(), CONSUMPTION_APPROBATION_DATE_NOT_NULL).atOffset(ZoneOffset.UTC)
        );
    }
}

