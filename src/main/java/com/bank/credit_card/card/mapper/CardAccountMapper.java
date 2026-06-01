package com.bank.credit_card.card.mapper;

import com.bank.credit_card.card.dto.CardDtoRequest;
import com.bank.credit_card.card.entity.CardAccountEntity;
import com.bank.credit_card.generic.mapper.EntityMapper;
import org.springframework.stereotype.Component;

import static com.bank.credit_card.card.enums.CardStatusEnum.OPERATIVE;

@Component
public class CardAccountMapper implements EntityMapper<CardDtoRequest, CardAccountEntity> {

    @Override
    public CardAccountEntity toEntity(CardDtoRequest dto) {

        return CardAccountEntity.builder()
                .cardId(dto.cardId())
                .cardAccountId(dto.cardAccountId())
                .creditTotal(dto.creditTotal())
                .debtTax(dto.debtTax())
                .currency(dto.currency())
                .paymentDate(dto.paymentDay())
                .cardStatus(OPERATIVE)
                .build();
    }
}

