package com.bank.credit_card.consumptions.mapper;

import com.bank.credit_card.consumptions.dto.request.SplitConsumptionDebtRequestDto;
import com.bank.credit_card.consumptions.schema.request.SplitConsumptionDebtRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SplitConsumptionDebtMapper {

    SplitConsumptionDebtRequestDto toDto(SplitConsumptionDebtRequest splitConsumptionDebtRequest);
}
