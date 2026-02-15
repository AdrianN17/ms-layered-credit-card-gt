package com.bank.credit_card.balances.mapper;

import com.bank.credit_card.balances.dto.BalanceDto;
import com.bank.credit_card.balances.entity.BalanceEntity;
import com.bank.credit_card.balances.schema.response.BalanceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

    BalanceEntity toEntity(BalanceDto dto);

    BalanceDto toDto(BalanceEntity entity);

    BalanceResponse toResponse(BalanceDto dto);
}
