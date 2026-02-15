package com.bank.credit_card.consumptions.mapper;

import com.bank.credit_card.consumptions.dto.SplitConsumptionDebtDto;
import com.bank.credit_card.consumptions.schema.request.SplitConsumptionDebtRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SplitConsumptionDebtMapper {

    SplitConsumptionDebtDto toDto(SplitConsumptionDebtRequest splitConsumptionDebtRequest);
}
