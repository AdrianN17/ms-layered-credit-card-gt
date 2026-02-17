package com.bank.credit_card.clients.mapper;

import com.bank.credit_card.clients.commons.CardStatus;
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

    @Mapping(source = "dto.cardId", target = "cardId")
    @Mapping(source = "dto.crediticialTotalAmount", target = "crediticialTotalAmount")
    @Mapping(source = "dto.debtTax", target = "debtTax")
    @Mapping(source = "dto.currency", target = "currency")
    @Mapping(source = "dto.facturationDate", target = "facturationDate")
    @Mapping(source = "dto.paymentDate", target = "paymentDate")
    @Mapping(source = "cardStatus", target = "cardStatus")
    CardAccountEntity toEntity(CardAccountRequestDto dto, CardStatus cardStatus);

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
    @Mapping(source="cardStatus", target="cardStatus", qualifiedByName = "cardStatusToString")
    CardAccountResponse toResponse(CardAccountResponseDto cardAccountResponseDto);
}
