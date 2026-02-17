package com.bank.credit_card.consumptions.service;

import com.bank.credit_card.consumptions.dto.request.ConsumptionRequestDto;
import com.bank.credit_card.consumptions.mapper.ConsumptionMapper;
import com.bank.credit_card.consumptions.repository.ConsumptionRepository;
import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.generic.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsumptionServiceImpl extends GenericServiceImpl implements ConsumptionService {

    private final ConsumptionRepository consumptionRepository;
    private final ConsumptionMapper consumptionMapper;

    @Override
    public Long create(ConsumptionRequestDto consumptionDto) {
        var consumption = consumptionRepository.save(consumptionMapper.toEntity(consumptionDto));
        return consumption.getConsumptionId();
    }

    @Override
    public Long close(Long consumptionId) {
        var consumption = consumptionRepository.findById(consumptionId)
                .orElseThrow(() ->
                        new CustomBadRequest("Consumption not found"));
        consumption.softDelete();
        consumptionRepository.save(consumption);
        return consumption.getConsumptionId();
    }
}
