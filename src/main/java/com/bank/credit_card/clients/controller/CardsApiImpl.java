package com.bank.credit_card.clients.controller;

import com.bank.credit_card.clients.mapper.CardAccountMapper;
import com.bank.credit_card.clients.mapper.CardMapper;
import com.bank.credit_card.clients.schema.request.CreateCardRequest;
import com.bank.credit_card.clients.service.CardService;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CardsApiImpl implements CardsApi{

    private final CardService cardService;
    private final CardMapper cardMapper;
    private final CardAccountMapper cardAccountMapper;

    @Override
    public ResponseEntity<Tracking> cloneCard(Long cardId) {

        cardService.cloneCard(cardId);
        return null;
    }

    @Override
    public ResponseEntity<Tracking> closeCard(Long cardId) {

        cardService.closeCard(cardId);
        return null;
    }

    @Override
    public ResponseEntity<Tracking> createCard(Long clientId, CreateCardRequest createCardRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        var cardAccountDto= cardAccountMapper.toDto(createCardRequest.getCardAccount());
        var cardDto= cardMapper.toDto(createCardRequest.getCard(), cardAccountDto);

        cardService.createCardForClient(cardDto, clientId);
        return null;
    }
}
