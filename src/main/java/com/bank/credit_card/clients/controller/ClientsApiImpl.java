package com.bank.credit_card.clients.controller;

import com.bank.credit_card.clients.delegate.ClientsApiDelegate;
import com.bank.credit_card.clients.schema.request.CreateClientRequest;
import com.bank.credit_card.clients.schema.response.ClientResponse;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClientsApiImpl implements ClientsApi {

    private final ClientsApiDelegate clientsApiDelegate;

    @Override
    public ResponseEntity<Tracking> closeClient(Long clientId) {
        return clientsApiDelegate.closeClient(clientId);
    }

    @Override
    public ResponseEntity<Tracking> createClient(CreateClientRequest createClientRequest, BindingResult bindingResult) {
        return clientsApiDelegate.createClient(createClientRequest, bindingResult);
    }

    @Override
    public ResponseEntity<ClientResponse> getClients(Long clientId) {
        return clientsApiDelegate.getClients(clientId);
    }
}
