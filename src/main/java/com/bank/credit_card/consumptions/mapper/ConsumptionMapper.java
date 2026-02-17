package com.bank.credit_card.consumptions.mapper;

import com.bank.credit_card.consumptions.dto.request.ConsumptionRequestDto;
import com.bank.credit_card.consumptions.entity.ConsumptionEntity;
import com.bank.credit_card.consumptions.schema.request.ConsumptionRequest;
import com.bank.credit_card.generic.util.EnumGenericUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {EnumGenericUtility.class})
public interface ConsumptionMapper {

    ConsumptionEntity toEntity(ConsumptionRequestDto dto);

    @Mapping(target = "cardId", source = "cardId")
    @Mapping(target = "consumptionDate", source = "consumptionDate")
    @Mapping(target = "amount", source = "consumptionRequest.amount")
    @Mapping(target = "currency", source = "consumptionRequest.currency", qualifiedByName = "mapToCurrency")
    @Mapping(target = "sellerName", source = "consumptionRequest.sellerName")
    ConsumptionRequestDto toDto(ConsumptionRequest consumptionRequest, Long cardId, LocalDateTime consumptionDate);

}
