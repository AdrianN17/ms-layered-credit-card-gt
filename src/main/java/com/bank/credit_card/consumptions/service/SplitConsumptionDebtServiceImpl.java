package com.bank.credit_card.consumptions.service;

import com.bank.credit_card.consumptions.dto.request.ConsumptionRequestDto;
import com.bank.credit_card.consumptions.dto.request.SplitConsumptionDebtRequestDto;
import com.bank.credit_card.consumptions.mapper.ConsumptionMapper;
import com.bank.credit_card.consumptions.repository.ConsumptionRepository;
import com.bank.credit_card.consumptions.repository.TaxVORepository;
import com.bank.credit_card.exceptions.CustomBadRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.IntStream;

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
                        new CustomBadRequest("Consumption not found"));

        var amount = consumptionEntity.getAmount();
        var date = consumptionEntity.getConsumptionApprobationDae();
        var tax = taxVORepository.getDebtTaxByConsumptionId(consumptionEntity.getCardId());
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
