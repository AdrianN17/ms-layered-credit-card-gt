package com.bank.credit_card.consumptions.service;

import com.bank.credit_card.consumptions.dto.request.ConsumptionRequestDto;
import com.bank.credit_card.consumptions.dto.request.SplitConsumptionDebtRequestDto;
import com.bank.credit_card.consumptions.mapper.ConsumptionMapper;
import com.bank.credit_card.consumptions.repository.ConsumptionRepository;
import com.bank.credit_card.consumptions.repository.TaxVORepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static com.bank.credit_card.consumptions.constant.ConsumptionConstant.CONSUMPTION_NOT_FOUND;
import static com.bank.credit_card.consumptions.constant.ConsumptionConstant.TAX_ASSOCIATED_CARD_ACCOUNT_NOT_FOUND;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownBadRequest;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownNotFound;

@Service
@AllArgsConstructor
public class SplitConsumptionDebtServiceImpl implements SplitConsumptionDebtService {

    private final ConsumptionRepository consumptionRepository;
    private final ConsumptionMapper consumptionMapper;
    private final TaxVORepository taxVORepository;

    @Override
    @Transactional
    public void split(Long consumptionId, SplitConsumptionDebtRequestDto splitConsumptionDebtRequestDto) {
        var consumptionEntity = consumptionRepository.findById(consumptionId)
                .orElseThrow(() ->
                        thrownNotFound(CONSUMPTION_NOT_FOUND));

        var amount = consumptionEntity.getAmount();
        var date = consumptionEntity.getConsumptionApprobationDate();
        var tax = taxVORepository.getDebtTaxByConsumptionId(consumptionEntity.getCardId())
                .orElseThrow(() -> thrownBadRequest(TAX_ASSOCIATED_CARD_ACCOUNT_NOT_FOUND));
        var amountSplit = amount.divide(BigDecimal.valueOf(splitConsumptionDebtRequestDto.quantity()))
                .add(amount.multiply(tax));


        IntStream.rangeClosed(1, splitConsumptionDebtRequestDto.quantity())
                .mapToObj(i -> {

                    var newDate = date.plusMonths(i);

                    return ConsumptionRequestDto.builder()
                            .cardId(consumptionEntity.getCardId())
                            .sellerName(consumptionEntity.getSellerName())
                            .currency(consumptionEntity.getCurrency())
                            .consumptionDate(newDate)
                            .amount(amountSplit)
                            .build();
                })
                .map(consumptionMapper::toEntity)
                .forEach(consumptionRepository::save);

        consumptionEntity.softDelete();
    }
}
