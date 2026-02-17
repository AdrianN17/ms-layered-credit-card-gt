package com.bank.credit_card.clients.delegate;

import com.bank.credit_card.clients.mapper.CardAccountMapper;
import com.bank.credit_card.clients.mapper.CardMapper;
import com.bank.credit_card.clients.mapper.ClientMapper;
import com.bank.credit_card.clients.schema.request.CreateClientRequest;
import com.bank.credit_card.clients.schema.response.ClientResponse;
import com.bank.credit_card.clients.service.CardAccountService;
import com.bank.credit_card.clients.service.CardService;
import com.bank.credit_card.clients.service.ClientService;
import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@AllArgsConstructor
public class ClientsApiDelegateImpl implements ClientsApiDelegate {
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final CardService cardService;
    private final CardMapper cardMapper;
    private final CardAccountService cardAccountService;
    private final CardAccountMapper cardAccountMapper;

    @Override
    public ResponseEntity<Tracking> closeClient(Long clientId) {
        cardAccountService.close(cardService.close(clientService.close(clientId)));
        return null;
    }

    @Override
    public ResponseEntity<Tracking> createClient(CreateClientRequest createClientRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            throw new CustomBadRequest(bindingResult);

        var clientDto = clientMapper.toDto(createClientRequest.getClient());
        var clientId = clientService.create(clientDto);
        var cardDto= cardMapper.toDto(createClientRequest.getCard(), clientId);
        var cardId = cardService.create(cardDto);
        var cardAccountDto= cardAccountMapper.toDto(createClientRequest.getCardAccount(), cardId);
        var cardAccountId = cardAccountService.create(cardAccountDto);

        return null;
    }

    @Override
    public ResponseEntity<ClientResponse> getClients(Long clientId) {

        var client = clientMapper.toResponse(clientService.getClient(clientId));

        return null;
    }
}
