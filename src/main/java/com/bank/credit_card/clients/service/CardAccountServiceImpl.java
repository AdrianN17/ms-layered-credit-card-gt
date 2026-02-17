package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.request.CardAccountRequestDto;
import com.bank.credit_card.clients.mapper.CardAccountMapper;
import com.bank.credit_card.clients.repository.CardAccountRepository;
import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.generic.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardAccountServiceImpl extends GenericServiceImpl implements CardAccountService {

    private final CardAccountRepository cardAccountRepository;
    private final CardAccountMapper cardAccountMapper;

    /*@Override
    @Transactional
    @Deprecated
    public CloningDto clone(CloningDto cloningDto) {
        var cardAccountEntity = cardAccountRepository.findById(cloningDto.oldId())
                .orElseThrow(() -> new CustomBadRequest("Card Account not found"));

        var newCardAccountEntity = cardAccountEntity.duplicate(cloningDto.newId());
        cardAccountRepository.save(newCardAccountEntity);

        cardAccountEntity.softDelete();
        cardAccountRepository.save(cardAccountEntity);

        return new CloningDto(cardAccountEntity.getCardAccountId(), newCardAccountEntity.getCardAccountId());
    }*/

    @Override
    public Long getId(Long cardId) {
        return cardAccountRepository.findCardAccountIdByCardId(cardId).orElseThrow(() -> new CustomBadRequest("Card Account not found"));
    }

    @Override
    public Long create(CardAccountRequestDto cardAccountDto) {
        var cardAccount = cardAccountRepository.save(cardAccountMapper.toEntity(cardAccountDto));
        return cardAccount.getCardAccountId();
    }

    @Override
    public Long close(Long cardAccountId) {
        var cardAccountEntity = cardAccountRepository.findById(cardAccountId)
                .orElseThrow(() -> new RuntimeException("Card Account not found"));

        cardAccountEntity.softDelete();

        cardAccountRepository.save(cardAccountEntity);

        return cardAccountEntity.getCardAccountId();
    }
}
