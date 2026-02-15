package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.CardDto;

public interface CardService {
    void closeCard(Long cardId);

    void createCard(CardDto cardDto);

    void cloneCard(Long cardId);

    void createCardForClient(CardDto cardDto, Long clientId);
}
