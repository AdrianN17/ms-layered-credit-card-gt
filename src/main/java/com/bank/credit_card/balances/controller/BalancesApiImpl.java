package com.bank.credit_card.balances.controller;

import com.bank.credit_card.balances.schema.response.BalanceResponse;
import com.bank.credit_card.balances.service.BalanceService;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BalancesApiImpl implements BalancesApi{

    private final BalanceService balanceService;


    @Override
    public ResponseEntity<Tracking> changeBalance(Long idCard) {


        return null;
    }

    @Override
    public ResponseEntity<BalanceResponse> getBalance(String cardId) {
        return null;
    }
}
