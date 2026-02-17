package com.bank.credit_card.balances.delegate;

import com.bank.credit_card.balances.schema.response.BalanceResponse;
import com.bank.credit_card.balances.service.BalanceService;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BalancesDelegateImpl implements BalancesDelegate {

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
