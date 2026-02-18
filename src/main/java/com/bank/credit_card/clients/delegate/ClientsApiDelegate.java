package com.bank.credit_card.clients.delegate;

import com.bank.credit_card.clients.schema.request.CreateClientRequest;
import com.bank.credit_card.clients.schema.response.ClientResponse;
import com.bank.credit_card.generic.schema.response.Tracking;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ClientsApiDelegate {
    ResponseEntity<Tracking> closeClient(Long clientId);

    ResponseEntity<Tracking> createClient(CreateClientRequest createClientRequest, BindingResult bindingResult);

    ResponseEntity<ClientResponse> getClients(Long clientId);
}
