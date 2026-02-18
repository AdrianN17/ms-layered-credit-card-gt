package com.bank.credit_card.consumptions.service.business;

import com.bank.credit_card.consumptions.dto.request.ConsumptionRequestDto;
import com.bank.credit_card.consumptions.dto.request.SplitConsumptionDebtRequestDto;
import com.bank.credit_card.consumptions.mapper.ConsumptionMapper;
import com.bank.credit_card.consumptions.repository.ConsumptionRepository;
import com.bank.credit_card.generic.publish.publisher.GenericEventPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static com.bank.credit_card.consumptions.constant.ConsumptionConstant.CONSUMPTION_NOT_FOUND;
import static com.bank.credit_card.consumptions.constant.ConsumptionConstant.TAX_ASSOCIATED_CARD_ACCOUNT_NOT_FOUND;
import static com.bank.credit_card.consumptions.util.PublishConsumptionUtility.splitEvent;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownBadRequest;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownNotFound;

@Slf4j
@Service
@AllArgsConstructor
public class SplitConsumptionDebtBusinessImpl implements SplitConsumptionDebtBusiness {

    private final ConsumptionRepository consumptionRepository;
    private final ConsumptionMapper consumptionMapper;
    private final GenericEventPublisher genericEventPublisher;

    @Override
    @Transactional
    public void split(Long consumptionId, SplitConsumptionDebtRequestDto splitConsumptionDebtRequestDto) {
        var consumptionEntity = consumptionRepository.findById(consumptionId)
                .orElseThrow(() ->
                        thrownNotFound(CONSUMPTION_NOT_FOUND));

        var amount = consumptionEntity.getAmount();
        var date = consumptionEntity.getConsumptionApprobationDate();
        var tax = consumptionRepository.getDebtTaxByConsumptionId(consumptionEntity.getCardId())
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
                .forEach(consumptionE -> {
                    consumptionRepository.save(consumptionE);
                    splitEvent(genericEventPublisher,
                            consumptionE.getConsumptionId());
                });

        consumptionEntity.softDelete();
    }

}
