package com.bank.credit_card.balances.mapper;

import com.bank.credit_card.balances.dto.projection.ConsumptionProjection;
import com.bank.credit_card.balances.dto.projection.PaymentProjection;
import com.bank.credit_card.balances.dto.request.BalanceRequestDto;
import com.bank.credit_card.balances.dto.response.BalanceDataResponseDto;
import com.bank.credit_card.balances.dto.response.BalanceResponseDto;
import com.bank.credit_card.balances.entity.BalanceEntity;
import com.bank.credit_card.balances.schema.response.BalanceDataResponse;
import com.bank.credit_card.balances.schema.response.BalanceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

    BalanceEntity toEntity(BalanceRequestDto dto);

    BalanceRequestDto toDto(BalanceEntity entity);

    BalanceResponse toResponse(BalanceResponseDto dto);

    BalanceDataResponse toResponse(BalanceDataResponseDto dto);

    @Mapping(target = "balanceDataset", source = "balanceDataResponseDtos")
    @Mapping(target = "cardId", source = "entity.cardId")
    @Mapping(target = "balanceId", source = "entity.idBalance")
    @Mapping(target = "consumptionAmount", source = "entity.consumptionAmount")
    @Mapping(target = "paymentAmount", source = "entity.paymentAmount")
    @Mapping(target = "startDate", source = "entity.startDate")
    @Mapping(target = "endDate", source = "entity.endDate")
    @Mapping(target = "exchangeRate", source = "entity.exchangeRate")
    BalanceResponseDto toDto(BalanceEntity entity, List<BalanceDataResponseDto> balanceDataResponseDtos);

    BalanceDataResponseDto toDto(ConsumptionProjection dto);

    BalanceDataResponseDto toDto(PaymentProjection dto);
}

