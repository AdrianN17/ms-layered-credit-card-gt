package com.bank.credit_card.consumption.mapper;

import com.bank.credit_card.consumption.dto.ConsumptionRequestDto;
import com.bank.credit_card.consumption.dto.ConsumptionResponseDto;
import com.bank.credit_card.consumption.entity.ConsumptionEntity;
import com.bank.credit_card.consumption.entity.ConsumptionEntityCosmos;
import com.bank.credit_card.consumption.entity.ConsumptionEntityMongo;
import com.bank.credit_card.consumption.schema.request.ConsumptionRequest;
import com.bank.credit_card.consumption.schema.response.ConsumptionResponse;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class ConsumptionMapper {

    private final Environment environment;

    public ConsumptionRequestDto toDto(ConsumptionRequest request) {
        return new ConsumptionRequestDto(
                request.getSellerName(),
                CurrencyEnum.valueOf(request.getCurrency()),
                request.getAmount()
        );
    }

    public ConsumptionEntity toEntity(ConsumptionRequestDto dto, String cardId) {
        boolean isNew = Arrays.asList(environment.getActiveProfiles()).contains("new");

        if (isNew) {
            return ConsumptionEntityCosmos.builder()
                    .cardId(cardId)
                    .sellerName(dto.sellerName())
                    .currency(dto.currency())
                    .amount(dto.amount())
                    .build();
        } else {
            return ConsumptionEntityMongo.builder()
                    .cardId(cardId)
                    .sellerName(dto.sellerName())
                    .currency(dto.currency())
                    .amount(dto.amount())
                    .build();
        }
    }

    public ConsumptionEntity toEntityFromSplit(ConsumptionEntity original, String sellerName, BigDecimal amount, LocalDateTime consumptionDate) {
        boolean isNew = Arrays.asList(environment.getActiveProfiles()).contains("new");

        if (isNew) {
            return ConsumptionEntityCosmos.builder()
                    .cardId(original.getCardId())
                    .sellerName(sellerName)
                    .currency(original.getCurrency())
                    .amount(amount)
                    .consumptionDate(consumptionDate)
                    .build();
        } else {
            return ConsumptionEntityMongo.builder()
                    .cardId(original.getCardId())
                    .sellerName(sellerName)
                    .currency(original.getCurrency())
                    .amount(amount)
                    .consumptionDate(consumptionDate)
                    .build();
        }
    }

    public ConsumptionResponseDto toDto(ConsumptionEntity entity) {
        return new ConsumptionResponseDto(
                entity.getSellerName(),
                entity.getCurrency(),
                entity.getAmount(),
                entity.getConsumptionDate(),
                entity.getConsumptionApprobationDate()
        );
    }

    public ConsumptionResponse toResponse(ConsumptionResponseDto dto) {
        return new ConsumptionResponse(
                dto.sellerName(),
                dto.currency().name(),
                dto.amount(),
                dto.consumptionDate() != null ? dto.consumptionDate().atOffset(ZoneOffset.UTC) : null,
                dto.consumptionApprobationDate() != null ? dto.consumptionApprobationDate().atOffset(ZoneOffset.UTC) : null
        );
    }
}

