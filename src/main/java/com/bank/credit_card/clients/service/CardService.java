package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.request.CardRequestDto;
import com.bank.credit_card.clients.dto.request.CloningDto;
import com.bank.credit_card.generic.service.GenericService;

public interface CardService extends GenericService<CardRequestDto, Long> {
    CloningDto clone(Long cardId);
}
