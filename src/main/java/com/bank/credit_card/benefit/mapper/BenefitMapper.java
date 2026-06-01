package com.bank.credit_card.benefit.mapper;

import com.bank.credit_card.benefit.dto.BenefitRequestDto;
import com.bank.credit_card.benefit.entity.BenefitEntity;
import com.bank.credit_card.card.schema.request.CardRequestBenefit;
import com.bank.credit_card.generic.mapper.EntityMapper;
import com.bank.credit_card.generic.mapper.RequestIDDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class BenefitMapper implements
        RequestIDDtoMapper<CardRequestBenefit, BenefitRequestDto>,
        EntityMapper<BenefitRequestDto, BenefitEntity> {

    @Override
    public BenefitRequestDto toRequestDto(CardRequestBenefit request,
                                   Long cardId, Long id) {
        return new BenefitRequestDto(
                id,
                0,
                request.getHasDiscount(),
                request.getMultiplierPoints(),
                cardId
        );
    }

    @Override
    public BenefitEntity toEntity(BenefitRequestDto request) {
        return BenefitEntity.builder()
                .idBenefit(request.benefitId())
                .cardId(request.cardId())
                .hasDiscount(request.hasDiscount())
                .totalPoints(request.pointEarned())
                .multiplierPoints(request.multiplierPoints())
                .build();
    }
}

