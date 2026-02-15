package com.bank.credit_card.clients.mapper;

import com.bank.credit_card.clients.dto.CardAccountDto;
import com.bank.credit_card.clients.dto.CardDto;
import com.bank.credit_card.clients.entity.CardEntity;
import com.bank.credit_card.clients.schema.request.CardRequest;
import com.bank.credit_card.clients.schema.response.CardResponse;
import com.bank.credit_card.clients.util.EnumCardUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CardAccountMapper.class, EnumCardUtility.class})
public interface CardMapper {

    @Mapping(source = "cardAccountDto", target = "cardAccount")
    CardEntity toEntity(CardDto dto);

    @Mapping(source = "cardAccount", target = "cardAccountDto")
    CardDto toDto(CardEntity entity);

    @Mapping(target = "cardId", ignore = true)
    @Mapping(source = "cardRequest.typeCard", target = "typeCard", qualifiedByName = "mapToTypeCard")
    @Mapping(source = "cardRequest.categoryCard", target = "categoryCard", qualifiedByName = "mapToCategoryCard")
    @Mapping(source = "cardAccountDto", target = "cardAccountDto")
    CardDto toDto(CardRequest cardRequest, CardAccountDto cardAccountDto);

    @Mapping(source = "cardId", target = "cardId")
    @Mapping(source = "typeCard", target = "typeCard", qualifiedByName = "typeCardToString")
    @Mapping(source = "categoryCard", target = "categoryCard", qualifiedByName = "categoryCardToString")
    @Mapping(source = "numberCard", target = "numberCard")
    CardResponse toResponse(CardDto cardDto);
}
