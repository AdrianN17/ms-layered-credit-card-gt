package com.bank.credit_card.card.service;

import com.bank.credit_card.card.dto.CardDtoRequest;
import com.bank.credit_card.card.dto.CardDtoResponse;

import java.math.BigDecimal;

public interface CardService {
    Long save(CardDtoRequest request);

    CardDtoResponse find(Long id);

    void delete(Long id);

    BigDecimal getRatio(Long id);
}
