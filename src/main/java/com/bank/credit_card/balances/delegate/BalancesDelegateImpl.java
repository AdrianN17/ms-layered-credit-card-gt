package com.bank.credit_card.balances.delegate;

import com.bank.credit_card.balances.mapper.BalanceMapper;
import com.bank.credit_card.balances.schema.response.BalanceResponse;
import com.bank.credit_card.balances.service.BalanceService;
import com.bank.credit_card.balances.service.business.MakeBalanceBusiness;
import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.bank.credit_card.generic.util.GenericResponsesUtility.generateTracking;

@Component
@AllArgsConstructor
public class BalancesDelegateImpl implements BalancesDelegate {

    private final BalanceService balanceService;
    private final BalanceMapper balanceMapper;
    private final MakeBalanceBusiness makeBalanceBusiness;

    @Override
    public ResponseEntity<Tracking> changeBalance(Long cardId) {
        var balanceId = balanceService.getIdOptional(cardId);

        var balanceDto = makeBalanceBusiness.generateBalance(cardId);
        balanceService.create(balanceDto);

        balanceId.ifPresent(balanceService::close);
        return generateTracking();
    }

    @Override
    public ResponseEntity<BalanceResponse> getBalance(Long cardId) {
        var balanceId = balanceService.getId(cardId);
        var balanceResponse = balanceMapper.toResponse(balanceService.getBalance(balanceId));

        return ResponseEntity.ok(balanceResponse);
    }
}
