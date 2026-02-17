package com.bank.credit_card.clients.mapper;

import com.bank.credit_card.clients.dto.request.CardRequestDto;
import com.bank.credit_card.clients.dto.response.CardResponseDto;
import com.bank.credit_card.clients.entity.CardEntity;
import com.bank.credit_card.clients.entity.vo.CardEntityVO;
import com.bank.credit_card.clients.schema.request.CardRequest;
import com.bank.credit_card.clients.schema.response.CardResponse;
import com.bank.credit_card.clients.util.EnumCardUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CardAccountMapper.class, EnumCardUtility.class})
public interface CardMapper {


    CardEntity toEntity(CardRequestDto dto);

    @Mapping(source = "dto.clientId", target = "clientId")
    @Mapping(source = "dto.typeCard", target = "typeCard")
    @Mapping(source = "dto.categoryCard", target = "categoryCard")
    @Mapping(source = "dateCard", target = "dateCard")
    @Mapping(source = "cvv", target = "cvv")
    @Mapping(source = "numberCard", target = "numberCard")
    CardEntity toEntity(CardRequestDto dto, String numberCard, String cvv, String dateCard);

    CardRequestDto toDto(CardEntity entity);

    @Mapping(source = "cardRequest.typeCard", target = "typeCard", qualifiedByName = "mapToTypeCard")
    @Mapping(source = "cardRequest.categoryCard", target = "categoryCard", qualifiedByName = "mapToCategoryCard")
    @Mapping(source = "clientId", target = "clientId")
    CardRequestDto toDto(CardRequest cardRequest, Long clientId);

    @Mapping(source = "cardRequest.typeCard", target = "typeCard", qualifiedByName = "mapToTypeCard")
    @Mapping(source = "cardRequest.categoryCard", target = "categoryCard", qualifiedByName = "mapToCategoryCard")
    CardRequestDto toDto(CardRequest cardRequest);

    CardResponseDto toDto(CardEntityVO entity);


    @Mapping(source = "typeCard", target = "typeCard", qualifiedByName = "typeCardToString")
    @Mapping(source = "categoryCard", target = "categoryCard", qualifiedByName = "categoryCardToString")
    CardResponse toResponse(CardResponseDto cardResponseDto);
}
