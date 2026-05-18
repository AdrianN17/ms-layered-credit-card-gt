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

import java.util.Objects;

import static com.bank.credit_card.card.exception.CardErrorMessage.ACCOUNT_NOT_NULL;

@Component
public class CardMapper implements RequestMapper<CardRequest, CardDtoRequest>,
                                   EntityMapper<CardDtoRequest, CardEntity> {

    public CardDtoRequest toDto(CardRequest request) {

        var account = Objects.requireNonNull(request.getAccount(), ACCOUNT_NOT_NULL);

        return new CardDtoRequest(
            TypeCardEnum.ofCode(request.getTypeCard()),
            CategoryCardEnum.ofCode(request.getCategoryCard()),
            CurrencyEnum.ofCode(account.getCurrency()),
            account.getCreditTotal(),
            account.getDebtTax(),
            Short.valueOf(account.getPaymentDate())
        );
    }

    public CardEntity toEntity(CardDtoRequest dto) {
        return CardEntity.builder()
                .typeCard(dto.typeCard())
                .categoryCard(dto.categoryCard())
                .build();
    }
}
