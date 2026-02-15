package com.bank.credit_card.clients.controller;

import com.bank.credit_card.clients.mapper.CardAccountMapper;
import com.bank.credit_card.clients.mapper.CardMapper;
import com.bank.credit_card.clients.mapper.ClientMapper;
import com.bank.credit_card.clients.schema.request.CreateClientRequest;
import com.bank.credit_card.clients.schema.response.ClientResponse;
import com.bank.credit_card.clients.service.ClientService;
import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientsApiImpl implements ClientsApi{

    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final CardMapper cardMapper;
    private final CardAccountMapper cardAccountMapper;

    @Override
    public ResponseEntity<Tracking> closeClient(Long clientId) {

        clientService.closeClient(clientId);

        return null;
    }

    @Override
    public ResponseEntity<Tracking> createClient(CreateClientRequest createClientRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            throw new CustomBadRequest(bindingResult);

        var cardAccountDto= cardAccountMapper.toDto(createClientRequest.getCardAccount());
        var cardDto= cardMapper.toDto(createClientRequest.getCard(), cardAccountDto);
        var clientDto = clientMapper.toDto(createClientRequest.getClient(), List.of(cardDto));

        clientService.createClient(clientDto);

        return null;
    }

    @Override
    public ResponseEntity<ClientResponse> getClients(Long clientId) {

        var clients = clientMapper.toResponse(clientService.getClientById(clientId));

        return null;
    }
}
