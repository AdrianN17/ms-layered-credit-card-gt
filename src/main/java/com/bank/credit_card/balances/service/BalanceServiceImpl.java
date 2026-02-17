package com.bank.credit_card.balances.service;

import com.bank.credit_card.balances.dto.request.BalanceRequestDto;
import com.bank.credit_card.balances.dto.response.BalanceDataResponseDto;
import com.bank.credit_card.balances.dto.response.BalanceResponseDto;
import com.bank.credit_card.balances.mapper.BalanceMapper;
import com.bank.credit_card.balances.repository.BalanceRepository;
import com.bank.credit_card.generic.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static com.bank.credit_card.balances.constant.BalanceConstant.*;
import static com.bank.credit_card.generic.util.GenericDateUtility.getEndDay;
import static com.bank.credit_card.generic.util.GenericDateUtility.getStartDay;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownNotFound;

@Service
@AllArgsConstructor
public class BalanceServiceImpl extends GenericServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;

    @Override
    public Long create(BalanceRequestDto balanceRequestDto) {
        var balance = balanceRepository.save(balanceMapper.toEntity(balanceRequestDto));
        return balance.getIdBalance();
    }

    @Override
    public Optional<Long> getIdOptional(Long cardId) {
        return balanceRepository.findLastBalanceIdByCardId(cardId);
    }

    @Override
    public Long getId(Long cardId) {
        return balanceRepository.findLastBalanceIdByCardId(cardId)
                .orElseThrow(() -> thrownNotFound(BALANCE_OF_CARD_NOT_FOUND));
    }

    @Override
    public BalanceResponseDto getBalance(Long balanceId) {
        var balanceEntity = balanceRepository.findById(balanceId)
                .orElseThrow(() ->
                        thrownNotFound(BALANCE_NOT_FOUND));

        var startDate = getStartDay(balanceEntity.getStartDate());
        var endDate = getEndDay(balanceEntity.getEndDate());

        var totalPayment = balanceRepository.findPaymentsAmountByCardId(balanceEntity.getCardId(), startDate, endDate);
        var totalConsumption = balanceRepository.findConsumptionsAmountByCardId(balanceEntity.getCardId(), startDate, endDate);

        var totalPaymentConsumptionDto = Stream
                .concat(
                        totalPayment.stream(),
                        totalConsumption.stream()
                )
                .map(balanceMapper::toDto)
                .sorted(Comparator.comparing(BalanceDataResponseDto::date))
                .toList();

        return balanceMapper.toDto(balanceEntity, totalPaymentConsumptionDto);
    }

    @Override
    public Long close(Long balanceId) {
        var balanceEntity = balanceRepository.findById(balanceId)
                .orElseThrow(() ->
                        thrownNotFound(BALANCE_NOT_FOUND));

        balanceEntity.softDelete();
        balanceRepository.save(balanceEntity);

        return balanceEntity.getIdBalance();
    }

    @Override
    public BalanceRequestDto generateBalanceByCardId(Long cardId) {
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
