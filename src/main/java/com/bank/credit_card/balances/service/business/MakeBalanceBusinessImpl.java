package com.bank.credit_card.balances.service.business;

import com.bank.credit_card.balances.dto.request.BalanceRequestDto;
import com.bank.credit_card.balances.repository.BalanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static com.bank.credit_card.balances.constant.BalanceConstant.CARD_ACCOUNT_DATA_ID_NOT_FOUND;
import static com.bank.credit_card.generic.util.GenericDateUtility.getEndDay;
import static com.bank.credit_card.generic.util.GenericDateUtility.getStartDay;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownNotFound;

@Service
@AllArgsConstructor
public class MakeBalanceBusinessImpl implements MakeBalanceBusiness {

    private final BalanceRepository balanceRepository;

    @Override
    public BalanceRequestDto generateBalance(Long cardId) {
        var cardAccountData = balanceRepository.findLastCardAccountDataByCardId(cardId)
                .orElseThrow(() ->
                        thrownNotFound(CARD_ACCOUNT_DATA_ID_NOT_FOUND));

        var oldBalanceAvailable = balanceRepository.findOldBalanceIdById(cardId).orElse(cardAccountData.getTotalAmount());
        var startDate = getStartDay(cardAccountData.getFacturationDate());
        var endDate = getEndDay(startDate.minusMonths(1));
        var totalPayments = balanceRepository.findTotalPaymentAmountByCardId(cardId, startDate, endDate);
        var totalConsumptions = balanceRepository.findTotalConsumptionAmountByCardId(cardId, startDate, endDate);
        var exchangeRate = BigDecimal.valueOf(3.1F);

        BigDecimal totalPaymentsInCurrency = totalPayments.stream()
                .map(payment -> payment.getCurrencyEnum().equals(cardAccountData.getCurrency())
                        ? payment.getTotalAmount()
                        : payment.getTotalAmount().multiply(exchangeRate)
                        .setScale(2, RoundingMode.HALF_EVEN))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalConsumptionsInCurrency = totalConsumptions.stream()
                .map(consumption -> Objects.equals(cardAccountData.getCurrency(), consumption.getCurrencyEnum())
                        ? consumption.getTotalAmount()
                        : consumption.getTotalAmount().multiply(exchangeRate)
                        .setScale(2, RoundingMode.HALF_EVEN))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal availableAmount = oldBalanceAvailable
                .add(totalPaymentsInCurrency)
                .subtract(totalConsumptionsInCurrency);

        return BalanceRequestDto.builder()
                .cardId(cardId)
                .totalAmount(cardAccountData.getTotalAmount())
                .availableAmount(availableAmount)
                .oldAmount(oldBalanceAvailable)
                .paymentAmount(totalPaymentsInCurrency)
                .consumptionAmount(totalConsumptionsInCurrency)
                .exchangeRate(exchangeRate)
                .startDate(startDate.toLocalDate())
                .endDate(endDate.toLocalDate())
                .currency(cardAccountData.getCurrencyEnum())
                .build();
    }
}
