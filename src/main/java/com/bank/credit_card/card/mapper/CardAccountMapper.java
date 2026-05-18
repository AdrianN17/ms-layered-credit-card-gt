package com.bank.credit_card.card.mapper;

import com.bank.credit_card.card.dto.CardDtoRequest;
import com.bank.credit_card.card.entity.CardAccountEntity;
import org.springframework.stereotype.Component;

import static com.bank.credit_card.card.enums.CardStatusEnum.OPERATIVE;

@Component
public class CardAccountMapper {

    public CardAccountEntity toEntity(CardDtoRequest dto, Long cardId) {

        return CardAccountEntity.builder()
                .cardId(cardId)
                .creditTotal(dto.creditTotal())
                .debtTax(dto.debtTax())
                .currency(dto.currency())
                .paymentDate(dto.paymentDay())
                .cardStatus(OPERATIVE)
                .build();
    }
}

