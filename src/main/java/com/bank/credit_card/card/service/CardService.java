package com.bank.credit_card.card.service;

import com.bank.credit_card.card.dto.CardDtoRequest;
import com.bank.credit_card.card.dto.CardDtoResponse;
import com.bank.credit_card.card.enums.CardStatusEnum;
import com.bank.credit_card.card.enums.CategoryCardEnum;

import java.math.BigDecimal;

public interface CardService {
    Long save(CardDtoRequest request);

    CardDtoResponse find(Long id);

    void delete(Long id);

    BigDecimal getRatio(CategoryCardEnum categoryCardEnum);

    void validate(CardStatusEnum cardStatus);
}
