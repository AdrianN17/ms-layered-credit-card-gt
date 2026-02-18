package com.bank.credit_card.balances.service;

import com.bank.credit_card.balances.dto.request.BalanceRequestDto;
import com.bank.credit_card.balances.dto.response.BalanceDataResponseDto;
import com.bank.credit_card.balances.dto.response.BalanceResponseDto;
import com.bank.credit_card.balances.mapper.BalanceMapper;
import com.bank.credit_card.balances.repository.BalanceRepository;
import com.bank.credit_card.generic.publish.publisher.GenericEventPublisher;
import com.bank.credit_card.generic.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import static com.bank.credit_card.balances.constant.BalanceConstant.*;
import static com.bank.credit_card.balances.util.PublishBalanceUtility.closeEvent;
import static com.bank.credit_card.balances.util.PublishBalanceUtility.createEvent;
import static com.bank.credit_card.generic.util.GenericDateUtility.getEndDay;
import static com.bank.credit_card.generic.util.GenericDateUtility.getStartDay;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownNotFound;

@Service
@AllArgsConstructor
public class BalanceServiceImpl extends GenericServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;
    private final GenericEventPublisher genericEventPublisher;

    @Override
    public Long create(BalanceRequestDto balanceRequestDto) {
        var balance = balanceRepository.save(balanceMapper.toEntity(balanceRequestDto));
        createEvent(genericEventPublisher, balance.getIdBalance());
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
        closeEvent(genericEventPublisher, balanceId);
        return balanceEntity.getIdBalance();
    }
}
