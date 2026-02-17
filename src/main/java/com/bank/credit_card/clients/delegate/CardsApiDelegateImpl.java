package com.bank.credit_card.clients.delegate;

import com.bank.credit_card.clients.mapper.CardAccountMapper;
import com.bank.credit_card.clients.mapper.CardMapper;
import com.bank.credit_card.clients.schema.request.CreateCardRequest;
import com.bank.credit_card.clients.service.CardAccountService;
import com.bank.credit_card.clients.service.CardService;
import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@AllArgsConstructor
public class CardsApiDelegateImpl implements CardsApiDelegate {

    private final CardService cardService;
    private final CardMapper cardMapper;
    private final CardAccountService cardAccountService;
    private final CardAccountMapper cardAccountMapper;


    @Override
    public ResponseEntity<Tracking> cloneCard(Long cardId) {
        cardAccountService.clone(cardService.clone(cardId));
        return null;
    }

    @Override
    public ResponseEntity<Tracking> closeCard(Long cardId) {
        cardService.close(cardId);
        var cardAccountId = cardAccountService.getId(cardId);
        cardAccountService.close(cardAccountId);
        return null;
    }

    @Override
    public ResponseEntity<Tracking> createCard(Long clientId, CreateCardRequest createCardRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomBadRequest(bindingResult);
        }

        var cardDto = cardMapper.toDto(createCardRequest.getCard(), clientId);
        var cardId = cardService.create(cardDto);

        var cardAccountDto = cardAccountMapper.toDto(createCardRequest.getCardAccount(), cardId);
        cardAccountService.create(cardAccountDto);
        return null;
    }
}
