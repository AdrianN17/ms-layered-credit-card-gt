package com.bank.credit_card.balances.controller;

import com.bank.credit_card.balances.delegate.BalancesDelegate;
import com.bank.credit_card.balances.schema.response.BalanceResponse;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BalancesApiImpl implements BalancesApi {
    private final BalancesDelegate balancesDelegate;

    @Override
    public ResponseEntity<Tracking> changeBalance(Long cardId) {
        return balancesDelegate.changeBalance(cardId);
    }

    @Override
    public ResponseEntity<BalanceResponse> getBalance(Long cardId) {
        return balancesDelegate.getBalance(cardId);
    }
}
