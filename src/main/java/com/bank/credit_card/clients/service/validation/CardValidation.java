package com.bank.credit_card.clients.service.validation;

import com.bank.credit_card.clients.repository.CardRepository;
import com.bank.credit_card.exceptions.CustomBadRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CardValidation {

    private final CardRepository cardRepository;

    public void validateMaxCards(Long clientId) {
        var countCards = cardRepository.countByClientId(clientId);
        Optional.of(countCards)
                .filter(c -> c >= 3)
                .ifPresent(c -> {
                    throw new CustomBadRequest("Client already has the maximum number of cards allowed");
                });
    }

    public void validateCardTypeAndCategory(Long clientId, Integer typeCard, Integer categoryCard) {
        var countCards = cardRepository.countByClientId(clientId, typeCard, categoryCard);
        Optional.of(countCards)
                .filter(c -> c >= 1)
                .ifPresent(c -> {
                    throw new CustomBadRequest("Client already has a card with the same type and category");
                });
    }

    public void validateActiveCardWithStatusTwo(Long cardId) {
        var overcharged = cardRepository.existsActiveCardOvercharge(cardId);
        Optional.of(overcharged)
                .filter(Boolean::booleanValue)
                .ifPresent(b -> {
                    throw new CustomBadRequest("Client already has an active card with status 2");
                });
    }

}
