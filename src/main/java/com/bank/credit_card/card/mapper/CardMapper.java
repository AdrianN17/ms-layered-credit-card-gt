package com.bank.credit_card.card.mapper;

import com.bank.credit_card.card.dto.CardDtoRequest;
import com.bank.credit_card.card.entity.CardEntity;
import com.bank.credit_card.card.enums.CategoryCardEnum;
import com.bank.credit_card.card.enums.TypeCardEnum;
import com.bank.credit_card.card.schema.request.CardRequest;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.generic.mapper.EntityMapper;
import com.bank.credit_card.generic.mapper.RequestMapper;
import org.springframework.stereotype.Component;

@Component
public class CardMapper implements RequestMapper<CardRequest, CardDtoRequest>,
                                   EntityMapper<CardDtoRequest, CardEntity> {

    public CardDtoRequest toDto(CardRequest request)
    {
        return new CardDtoRequest(
            TypeCardEnum.valueOf(request.getTypeCard()),
            CategoryCardEnum.valueOf(request.getCategoryCard()),
            CurrencyEnum.valueOf(request.getAccount().getCurrency()),
            request.getAccount().getCreditTotal(),
            request.getAccount().getDebtTax(),
            Short.valueOf(request.getAccount().getPaymentDate())
            );
    }

    public CardEntity toEntity(CardDtoRequest dto) {
        return CardEntity.builder()
                .typeCard(dto.typeCard())
                .categoryCard(dto.categoryCard())
                .build();
    }
}

