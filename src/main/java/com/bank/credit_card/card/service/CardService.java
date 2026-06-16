package com.bank.credit_card.card.service;

import com.bank.credit_card.card.dto.CardDtoRequest;
import com.bank.credit_card.card.dto.CardDtoResponse;
import com.bank.credit_card.card.enums.CardStatusEnum;
import com.bank.credit_card.card.enums.CategoryCardEnum;
import com.bank.credit_card.generic.service.GenericService;

import java.math.BigDecimal;
import java.util.UUID;

public interface CardService extends GenericService<CardDtoRequest, Long> {

    CardDtoResponse find(Long id);

    BigDecimal getRatio(CategoryCardEnum categoryCardEnum);

    void validate(CardStatusEnum cardStatus);
}
