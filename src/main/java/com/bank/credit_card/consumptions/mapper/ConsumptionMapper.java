package com.bank.credit_card.consumptions.mapper;

import com.bank.credit_card.consumptions.dto.ConsumptionDto;
import com.bank.credit_card.consumptions.entity.ConsumptionEntity;
import com.bank.credit_card.consumptions.schema.request.ConsumptionRequest;
import com.bank.credit_card.generic.util.EnumGenericUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EnumGenericUtility.class})
public interface ConsumptionMapper {

    ConsumptionEntity toEntity(ConsumptionDto dto);

    ConsumptionDto toDto(ConsumptionEntity entity);

    @Mapping(target = "consumptionId", ignore = true)
    @Mapping(target = "cardId", source="cardId")
    @Mapping(target = "amount", source="consumptionRequest.amount")
    @Mapping(target = "currency", source="consumptionRequest.currency", qualifiedByName = "mapToCurrency")
    @Mapping(target = "sellerName", source="consumptionRequest.sellerName")
    @Mapping(target = "consumptionDate", ignore = true)
    @Mapping(target = "consumptionApprobationDae", ignore = true)
    ConsumptionDto toDto(ConsumptionRequest consumptionRequest, Long cardId);

}
