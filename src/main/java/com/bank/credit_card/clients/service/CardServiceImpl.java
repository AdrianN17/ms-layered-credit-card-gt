package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.CardDto;
import com.bank.credit_card.clients.entity.CardEntity;
import com.bank.credit_card.clients.mapper.CardMapper;
import com.bank.credit_card.clients.repository.CardRepository;
import com.bank.credit_card.exceptions.CustomBadRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final ClientService clientService;

    @Override
    @Transactional
    public void closeCard(Long cardId) {

        var cardEntity = cardRepository.findById(cardId)
                .orElseThrow(() -> new CustomBadRequest("Card not found"));

        cardEntity.softDelete();
        cardRepository.save(cardEntity);

    }

    @Override
    public void createCard(CardDto cardDto) {
        cardRepository.save(cardMapper.toEntity(cardDto));
    }

    @Override
    @Transactional
    public void cloneCard(Long cardId) {

        var cardEntity = cardRepository.findById(cardId)
                .orElseThrow(() -> new CustomBadRequest("Card not found"));

        CardEntity newCardEntity = cardEntity.duplicate();
        cardRepository.save(newCardEntity);

        cardEntity.softDelete();

        if (!isNull(cardEntity.getCardAccount())) {
            cardEntity.getCardAccount().softDelete();
        }

    }

    @Override
    public void createCardForClient(CardDto cardDto, Long clientId) {
        clientService.registerCardToClient(cardDto, clientId);
    }
}
