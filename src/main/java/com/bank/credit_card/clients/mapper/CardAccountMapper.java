package com.bank.credit_card.clients.mapper;

import com.bank.credit_card.clients.dto.request.CardAccountRequestDto;
import com.bank.credit_card.clients.dto.response.CardAccountResponseDto;
import com.bank.credit_card.clients.entity.CardAccountEntity;
import com.bank.credit_card.clients.entity.vo.CardAccountEntityVO;
import com.bank.credit_card.clients.schema.request.CardAccountRequest;
import com.bank.credit_card.clients.schema.response.CardAccountResponse;
import com.bank.credit_card.clients.util.EnumCardUtility;
import com.bank.credit_card.generic.util.EnumGenericUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EnumGenericUtility.class, EnumCardUtility.class})
public interface CardAccountMapper {

    CardAccountEntity toEntity(CardAccountRequestDto dto);

    CardAccountRequestDto toDto(CardAccountEntity entity);


    @Mapping(source = "cardAccountRequest.crediticialTotalAmount", target = "crediticialTotalAmount")
    @Mapping(source = "cardAccountRequest.debtTax", target = "debtTax")
    @Mapping(source = "cardAccountRequest.currency", target = "currency", qualifiedByName = "mapToCurrency")
    @Mapping(source = "cardAccountRequest.facturationDate", target = "facturationDate")
    @Mapping(source = "cardAccountRequest.paymentDate", target = "paymentDate")
    @Mapping(source = "cardId", target = "cardId")
    CardAccountRequestDto toDto(CardAccountRequest cardAccountRequest, Long cardId);


    CardAccountResponseDto toDto(CardAccountEntityVO entity);


    @Mapping(source = "crediticialTotalAmount", target = "crediticialTotalAmount")
    @Mapping(source = "currency", target = "currency", qualifiedByName = "currencyToString")
    CardAccountResponse toResponse(CardAccountResponseDto cardAccountResponseDto);
}
