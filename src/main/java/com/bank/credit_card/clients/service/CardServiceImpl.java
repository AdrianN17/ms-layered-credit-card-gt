package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.request.CardRequestDto;
import com.bank.credit_card.clients.mapper.CardMapper;
import com.bank.credit_card.clients.repository.CardRepository;
import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.generic.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CardServiceImpl extends GenericServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    @Transactional
    public Long close(Long cardId) {

        var cardEntity = cardRepository.findById(cardId)
                .orElseThrow(() -> new CustomBadRequest("Card not found"));

        cardEntity.softDelete();
        cardRepository.save(cardEntity);

        return cardEntity.getCardId();
    }

    @Override
    public Long create(CardRequestDto cardDto) {
        var card = cardRepository.save(cardMapper.toEntity(cardDto));
        return card.getCardId();
    }

    /*@Override
    @Transactional
    @Deprecated
    public CloningDto clone(Long cardId) {

        var cardEntity = cardRepository.findById(cardId)
                .orElseThrow(() -> new CustomBadRequest("Card not found"));

        CardEntity newCardEntity = cardEntity.duplicate();
        cardRepository.save(newCardEntity);

        cardEntity.softDelete();
        cardRepository.save(cardEntity);

        return new CloningDto(cardEntity.getCardId(), newCardEntity.getCardId());
    }*/
}
