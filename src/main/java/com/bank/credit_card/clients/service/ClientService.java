package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.CardDto;
import com.bank.credit_card.clients.dto.ClientDto;

public interface ClientService {

    void createClient(ClientDto clientDto);

    void closeClient(Long clientId);

    ClientDto getClientById(Long clientId);

    void registerCardToClient(CardDto cardDto, Long clientId);
}

