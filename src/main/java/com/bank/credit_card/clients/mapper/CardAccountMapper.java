package com.bank.credit_card.clients.mapper;

import com.bank.credit_card.clients.dto.CardAccountDto;
import com.bank.credit_card.clients.entity.CardAccountEntity;
import com.bank.credit_card.clients.schema.request.CardAccountRequest;
import com.bank.credit_card.clients.schema.response.CardAccountResponse;
import com.bank.credit_card.clients.util.EnumCardUtility;
import com.bank.credit_card.generic.util.EnumGenericUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EnumGenericUtility.class, EnumCardUtility.class})
public interface CardAccountMapper {

    CardAccountEntity toEntity(CardAccountDto dto);

    CardAccountDto toDto(CardAccountEntity entity);

    @Mapping(target = "cardAccountId", ignore = true)
    @Mapping(source = "crediticialTotalAmount", target = "crediticialTotalAmount")
    @Mapping(source = "debtTax", target = "debtTax")
    @Mapping(source = "currency", target = "currency", qualifiedByName = "mapToCurrency")
    @Mapping(source = "facturationDate", target = "facturationDate")
    @Mapping(source = "paymentDate", target = "paymentDate")
    CardAccountDto toDto(CardAccountRequest cardAccountRequest);


    @Mapping(source = "cardAccountId", target = "cardAccountId")
    @Mapping(source = "crediticialTotalAmount", target = "crediticialTotalAmount")
    @Mapping(source = "debtTax", target = "debtTax")
    @Mapping(source = "currency", target = "currency", qualifiedByName = "currencyToString")
    @Mapping(source = "facturationDate", target = "facturationDate")
    @Mapping(source = "paymentDate", target = "paymentDate")
    CardAccountResponse toResponse(CardAccountDto cardAccountDto);
}
