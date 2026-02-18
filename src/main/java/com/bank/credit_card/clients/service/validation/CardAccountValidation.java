package com.bank.credit_card.clients.service.validation;

import com.bank.credit_card.clients.repository.CardAccountRepository;
import com.bank.credit_card.exceptions.CustomBadRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CardAccountValidation {

    private final CardAccountRepository cardAccountRepository;

    public void cardAccountNotHaveDebt(Long cardAccountId) {
        Optional.of(cardAccountRepository.existsInDebtCard(cardAccountId))
                .ifPresent(b -> {
                    throw new CustomBadRequest("User have debt");
                });
    }
}
