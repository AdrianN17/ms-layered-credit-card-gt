package com.bank.credit_card.benefit.mapper;

import com.bank.credit_card.benefit.dto.BenefitRequestDto;
import com.bank.credit_card.benefit.entity.BenefitEntity;
import org.springframework.stereotype.Component;

@Component
public class BenefitMapper {

    public BenefitEntity toEntity(BenefitRequestDto request, Long cardId) {
        return BenefitEntity.builder()
                .idBenefit(1L)
                .cardId(cardId)
                .hasDiscount(request.hasDiscount())
                .totalPoints(request.pointEarned())
                .multiplierPoints(request.multiplierPoints())
                .build();
    }
}

