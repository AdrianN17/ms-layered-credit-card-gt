package com.bank.credit_card.consumptions.service;

import com.bank.credit_card.consumptions.dto.ConsumptionDto;
import com.bank.credit_card.consumptions.dto.SplitConsumptionDebtDto;
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
public class ConsumptionServiceImpl implements ConsumptionService {

    private final ConsumptionRepository consumptionRepository;
    private final TaxVORepository taxVORepository;
    private final ConsumptionMapper consumptionMapper;

    @Override
    public void createConsumption(ConsumptionDto consumptionDto) {
        consumptionRepository.save(consumptionMapper.toEntity(consumptionDto));
    }

    @Override
    public void closeConsumption(Long consumptionId) {
        consumptionRepository.findById(consumptionId)
                .orElseThrow(() ->
                        new CustomBadRequest("Consumption not found"))
                .softDelete();
    }

    @Override
    @Transactional
    public void splitConsumption(Long consumptionId, SplitConsumptionDebtDto splitConsumptionDebtDto) {
        var consumptionEntity = consumptionRepository.findById(consumptionId)
                .orElseThrow(() ->
                        new CustomBadRequest("Consumption not found"));

        var amount = consumptionEntity.getAmount();
        var date = consumptionEntity.getConsumptionApprobationDae();
        var tax = taxVORepository.getDebtTaxByConsumptionId(consumptionEntity.getCardId());
        var amountSplit = amount.divide(BigDecimal.valueOf(splitConsumptionDebtDto.quantity()))
                .add(amount.multiply(tax));


        IntStream.rangeClosed(1, splitConsumptionDebtDto.quantity())
                .mapToObj(i -> {

                    var newDate = date.plusMonths(i);

                    return ConsumptionDto.builder()
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
