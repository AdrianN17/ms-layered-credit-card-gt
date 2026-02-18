package com.bank.credit_card.balances.service.business;

import com.bank.credit_card.balances.dto.request.BalanceRequestDto;
import com.bank.credit_card.balances.repository.BalanceRepository;
import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.exchange.client.FeignExchange;
import com.bank.credit_card.generic.commons.Currency;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.bank.credit_card.balances.constant.BalanceConstant.CARD_ACCOUNT_DATA_ID_NOT_FOUND;
import static com.bank.credit_card.generic.util.GenericDateUtility.getEndDay;
import static com.bank.credit_card.generic.util.GenericDateUtility.getStartDay;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownNotFound;

@Service
@AllArgsConstructor
public class MakeBalanceBusinessImpl implements MakeBalanceBusiness {

    private final BalanceRepository balanceRepository;
    private final FeignExchange feignExchange;

    @Override
    public BalanceRequestDto generateBalance(Long cardId) {
        var cardAccountData = balanceRepository.findLastCardAccountDataByCardId(cardId)
                .orElseThrow(() ->
                        thrownNotFound(CARD_ACCOUNT_DATA_ID_NOT_FOUND));

        var oldBalanceAvailable = balanceRepository.findOldBalanceIdById(cardId).orElse(BigDecimal.ZERO);
        var startDate = getStartDay(cardAccountData.getFacturationDate());
        var endDate = getEndDay(startDate.minusMonths(1));

        BigDecimal exchangeRate = feignExchange.getExchangeRate()
                .map(r -> (cardAccountData.getCurrencyEnum() == Currency.PEN) ? r.result().PEN() : r.result().USD())
                .orElseThrow(() -> new CustomBadRequest("Error fetching exchange rate from external service"));

        var totalPaymentsInCurrency = calculateCurrentPayment(cardId, cardAccountData.getCurrencyEnum(), exchangeRate, startDate, endDate);
        var totalConsumptionsInCurrency = calculateCurrentConsumption(cardId, cardAccountData.getCurrencyEnum(), exchangeRate, startDate, endDate);

        BigDecimal totalDebt = calculateDebt(totalPaymentsInCurrency, totalConsumptionsInCurrency, oldBalanceAvailable, cardAccountData.getDebtTax());
        BigDecimal availableAmount = calculateAvailableAmount(cardAccountData.getTotalAmount(),totalDebt);

        return BalanceRequestDto.builder()
                .cardId(cardId)
                .totalAmount(cardAccountData.getTotalAmount())
                .availableAmount(availableAmount)
                .oldAmount(totalDebt)
                .paymentAmount(totalPaymentsInCurrency)
                .consumptionAmount(totalConsumptionsInCurrency)
                .exchangeRate(exchangeRate)
                .startDate(startDate.toLocalDate())
                .endDate(endDate.toLocalDate())
                .currency(cardAccountData.getCurrencyEnum())
                .build();
    }


    private BigDecimal calculateCurrentPayment(Long cardId,
                                               Currency currency,
                                               BigDecimal exchangeRate,
                                               LocalDateTime startDate,
                                               LocalDateTime endDate) {
        var totalPayments = balanceRepository.findTotalPaymentAmountByCardId(cardId, startDate, endDate);

        return totalPayments.stream()
                .map(payment -> payment.getCurrencyEnum().equals(currency)
                        ? payment.getTotalAmount()
                        : payment.getTotalAmount().multiply(exchangeRate)
                        .setScale(2, RoundingMode.HALF_EVEN))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateCurrentConsumption(Long cardId,
                                                   Currency currency,
                                                   BigDecimal exchangeRate,
                                                   LocalDateTime startDate,
                                                   LocalDateTime endDate) {
        var totalConsumptions = balanceRepository.findTotalConsumptionAmountByCardId(cardId, startDate, endDate);

        return totalConsumptions.stream()
                .map(consumption -> Objects.equals(currency, consumption.getCurrencyEnum())
                        ? consumption.getTotalAmount()
                        : consumption.getTotalAmount().multiply(exchangeRate)
                        .setScale(2, RoundingMode.HALF_EVEN))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateDebt(BigDecimal totalPayments,
                                     BigDecimal totalConsumptions,
                                     BigDecimal oldBalance,
                                     BigDecimal debtTax) {
        BigDecimal totalDebt = totalPayments.subtract(totalConsumptions).add(oldBalance);

        if (totalDebt.compareTo(BigDecimal.ZERO) < 0) {
            totalDebt = totalDebt.multiply(BigDecimal.ONE.add(debtTax));
        }

        return totalDebt;
    }

    private BigDecimal calculateAvailableAmount(BigDecimal totalAmount, BigDecimal totalDebt) {
        return totalAmount.subtract(totalDebt);
    }
}
