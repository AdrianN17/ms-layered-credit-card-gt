package com.bank.credit_card.card.mapper;

import com.bank.credit_card.card.dto.CardDtoRequest;
import com.bank.credit_card.card.entity.CardEntity;
import com.bank.credit_card.generic.enums.StatusEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CardMapper {

    public CardEntity toEntity(CardDtoRequest dto) {
        return CardEntity.builder()
                .typeCard(dto.typeCard())
                .categoryCard(dto.categoryCard())
                .build();
    }
}

