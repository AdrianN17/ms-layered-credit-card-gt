package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.request.CardRequestDto;
import com.bank.credit_card.clients.dto.request.CloningDto;
import com.bank.credit_card.clients.mapper.CardMapper;
import com.bank.credit_card.clients.repository.CardRepository;
import com.bank.credit_card.clients.repository.procedure.CloneCardRepositoryCustom;
import com.bank.credit_card.generic.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bank.credit_card.clients.constant.CardAccountConstant.CARD_ACCOUNT_NOT_FOUND;
import static com.bank.credit_card.clients.constant.CardConstant.CARD_NOT_FOUND;
import static com.bank.credit_card.clients.util.CardGeneratorUtility.*;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownNotFound;

@Service
@AllArgsConstructor
public class CardServiceImpl extends GenericServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final CloneCardRepositoryCustom cloneCardRepositoryCustom;

    @Override
    @Transactional
    public Long close(Long cardId) {

        var cardEntity = cardRepository.findById(cardId)
                .orElseThrow(() -> thrownNotFound(CARD_NOT_FOUND));

        cardEntity.softDelete();
        cardRepository.save(cardEntity);

        return cardEntity.getCardId();
    }

    @Override
    public Long create(CardRequestDto cardDto) {
        var card = cardRepository.save(cardMapper.toEntity(cardDto,
                generateCardNumber(),
                generateCVV(),
                generateExpirationDate()));
        return card.getCardId();
    }

    @Override
    public CloningDto clone(Long cardId) {

        if (!cardRepository.existsById(cardId)) {
            throw thrownNotFound(CARD_ACCOUNT_NOT_FOUND);
        }
        var newCardId = cloneCardRepositoryCustom.cloneCard(cardId);
        return new CloningDto(cardId, newCardId);
    }
}
