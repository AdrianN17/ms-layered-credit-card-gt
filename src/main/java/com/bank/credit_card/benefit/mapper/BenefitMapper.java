package com.bank.credit_card.benefit.mapper;

import com.bank.credit_card.benefit.dto.BenefitRequestDto;
import com.bank.credit_card.benefit.entity.BenefitEntity;
import com.bank.credit_card.card.schema.request.CardRequestBenefit;
import com.bank.credit_card.generic.mapper.EntityMapper;
import org.springframework.stereotype.Component;

@Component
public class BenefitMapper implements EntityMapper<BenefitRequestDto, BenefitEntity> {

    public BenefitRequestDto toDto(CardRequestBenefit request,
                                   Long cardId) {
        return new BenefitRequestDto(
                0,
                request.getHasDiscount(),
                request.getMultiplierPoints(),
                cardId
        );
    }

    public BenefitEntity toEntity(BenefitRequestDto request) {
        return BenefitEntity.builder()
                .cardId(request.cardId())
                .hasDiscount(request.hasDiscount())
                .totalPoints(request.pointEarned())
                .multiplierPoints(request.multiplierPoints())
                .build();
    }
}

