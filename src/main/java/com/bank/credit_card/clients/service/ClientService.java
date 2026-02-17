package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.request.ClientRequestDto;
import com.bank.credit_card.clients.dto.response.ClientResponseDto;
import com.bank.credit_card.generic.service.GenericService;

public interface ClientService extends GenericService<ClientRequestDto, Long> {

    ClientResponseDto getClient(Long clientId);
}

