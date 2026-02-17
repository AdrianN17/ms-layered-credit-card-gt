package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.request.CardAccountRequestDto;
import com.bank.credit_card.clients.dto.request.CloningDto;
import com.bank.credit_card.generic.service.GenericService;

public interface CardAccountService extends GenericService<CardAccountRequestDto, Long> {
    Long getId(Long cardId);
    CloningDto clone(Long cardId);
}
