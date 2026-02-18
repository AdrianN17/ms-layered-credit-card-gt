package com.bank.credit_card.consumptions.service;

import com.bank.credit_card.consumptions.dto.request.ConsumptionRequestDto;
import com.bank.credit_card.consumptions.mapper.ConsumptionMapper;
import com.bank.credit_card.consumptions.repository.ConsumptionRepository;
import com.bank.credit_card.consumptions.repository.procedure.ConsumptionRepositoryCustom;
import com.bank.credit_card.consumptions.service.business.PointBusinessImpl;
import com.bank.credit_card.generic.publish.publisher.GenericEventPublisher;
import com.bank.credit_card.generic.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.bank.credit_card.consumptions.constant.ConsumptionConstant.CONSUMPTION_NOT_FOUND;
import static com.bank.credit_card.consumptions.util.PublishConsumptionUtility.*;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownNotFound;

@Service
@AllArgsConstructor
@Slf4j
public class ConsumptionServiceImpl extends GenericServiceImpl implements ConsumptionService {

    private final ConsumptionRepository consumptionRepository;
    private final ConsumptionMapper consumptionMapper;
    private final ConsumptionRepositoryCustom consumptionRepositoryCustom;
    private final PointBusinessImpl defaultPointsPolicy;
    private final GenericEventPublisher genericEventPublisher;

    @Override
    public Long create(ConsumptionRequestDto consumptionDto) {

        var consumption = consumptionRepository.save(consumptionMapper.toEntity(consumptionDto));
        createEvent(genericEventPublisher,
                consumption.getConsumptionId());
        publishPoints(genericEventPublisher,
                defaultPointsPolicy,
                consumptionRepository.getTypeCardByCardId(consumption.getCardId()),
                consumption.getConsumptionId(),
                consumption.getCardId(),
                consumption.getAmount());

        return consumption.getConsumptionId();
    }

    @Override
    public Long close(Long consumptionId) {
        var consumption = consumptionRepository.findById(consumptionId)
                .orElseThrow(() ->
                        thrownNotFound(CONSUMPTION_NOT_FOUND));
        consumption.softDelete();
        consumptionRepository.save(consumption);
        closeEvent(genericEventPublisher, consumptionId);
        return consumption.getConsumptionId();
    }

    @Override
    public void approbate(LocalDate targetDate,
                          LocalDateTime approbationDate) {
        var count = consumptionRepositoryCustom.approbate(targetDate, approbationDate);
        log.info("Approbated {} consumptions for target date {}", count, targetDate);
        approbateEvent(genericEventPublisher, approbationDate, count);
    }
}
