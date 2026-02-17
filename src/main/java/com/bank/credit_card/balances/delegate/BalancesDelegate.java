package com.bank.credit_card.balances.delegate;

import com.bank.credit_card.balances.schema.response.BalanceResponse;
import com.bank.credit_card.generic.schema.response.Tracking;
import org.springframework.http.ResponseEntity;

public interface BalancesDelegate {
    ResponseEntity<Tracking> changeBalance(Long idCard);
    ResponseEntity<BalanceResponse> getBalance(String cardId);
}
