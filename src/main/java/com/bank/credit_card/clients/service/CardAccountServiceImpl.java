package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.request.CardAccountRequestDto;
import com.bank.credit_card.clients.dto.request.CloningDto;
import com.bank.credit_card.clients.mapper.CardAccountMapper;
import com.bank.credit_card.clients.repository.CardAccountRepository;
import com.bank.credit_card.clients.repository.procedure.CloneCardAccountRepositoryCustom;
import com.bank.credit_card.generic.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bank.credit_card.clients.commons.CardStatus.OPERATIVA;
import static com.bank.credit_card.clients.constant.CardAccountConstant.CARD_ACCOUNT_NOT_FOUND;
import static com.bank.credit_card.clients.constant.CardAccountConstant.CARD_ACCOUNT_OF_CARD_NOT_FOUND;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownBadRequest;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownNotFound;

@Service
@AllArgsConstructor
public class CardAccountServiceImpl extends GenericServiceImpl implements CardAccountService {

    private final CardAccountRepository cardAccountRepository;
    private final CardAccountMapper cardAccountMapper;
    private final CloneCardAccountRepositoryCustom cloneCardAccountRepositoryCustom;

    @Override
    public Long getId(Long cardId) {
        return cardAccountRepository.findCardAccountIdByCardId(cardId)
                .orElseThrow(() -> thrownBadRequest(CARD_ACCOUNT_OF_CARD_NOT_FOUND));
    }

    @Override
    public CloningDto clone(Long cardAccountId) {
        if (!cardAccountRepository.existsById(cardAccountId)) {
            throw thrownNotFound(CARD_ACCOUNT_NOT_FOUND);
        }

        var newCardAccountId = cloneCardAccountRepositoryCustom.cloneCardAccount(cardAccountId);
        return new CloningDto(cardAccountId, newCardAccountId);
    }

    @Override
    public Long create(CardAccountRequestDto cardAccountDto) {
        var cardAccount = cardAccountRepository.save(cardAccountMapper.toEntity(cardAccountDto, OPERATIVA));
        return cardAccount.getCardAccountId();
    }

    @Override
    public Long close(Long cardAccountId) {
        var cardAccountEntity = cardAccountRepository.findById(cardAccountId)
                .orElseThrow(() -> thrownNotFound(CARD_ACCOUNT_NOT_FOUND));

        cardAccountEntity.softDelete();
        cardAccountRepository.save(cardAccountEntity);
        return cardAccountEntity.getCardAccountId();
    }
}
