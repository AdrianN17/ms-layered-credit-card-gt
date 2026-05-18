package com.bank.credit_card.benefit.service;

import com.bank.credit_card.benefit.dto.BenefitRequestDto;
import com.bank.credit_card.benefit.mapper.BenefitMapper;
import com.bank.credit_card.benefit.repository.BenefitJpaRepository;
import com.bank.credit_card.generic.enums.StatusEnum;
import com.bank.credit_card.generic.exception.BadRequestException;
import com.bank.credit_card.generic.exception.UnprocessableEntityException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.bank.credit_card.benefit.constant.BenefitConstant.DISCOUNT_PER_POINT;
import static com.bank.credit_card.benefit.exception.BenefitErrorMessage.AMOUNT_NOT_NULL;
import static com.bank.credit_card.benefit.exception.BenefitErrorMessage.BENEFIT_NOT_FOUND;
import static com.bank.credit_card.benefit.exception.BenefitErrorMessage.NOT_ENOUGH_POINTS;
import static com.bank.credit_card.generic.util.Validation.isNotConditional;

@Service
@AllArgsConstructor
public class BenefitServiceImpl implements BenefitService {

    private final BenefitJpaRepository benefitJpaRepository;
    private final BenefitMapper benefitMapper;

    @Override
    public Long save(BenefitRequestDto request, Long cardId) {
        var entity = benefitMapper.toEntity(request);
        var saved = benefitJpaRepository.save(entity);
        return saved.getIdBenefit();
    }

    @Override
    public void delete(Long cardId) {
        var entity = benefitJpaRepository.findActiveByCardId(cardId)
                .orElseThrow(() -> new UnprocessableEntityException(BENEFIT_NOT_FOUND));
        entity.setStatus(StatusEnum.INACTIVE);
        benefitJpaRepository.save(entity);
    }

    @Override
    public void accumulate(BigDecimal amount, BigDecimal ratio, Long cardId) {
        var entity = benefitJpaRepository.findActiveByCardId(cardId)
                .orElseThrow(() -> new UnprocessableEntityException(BENEFIT_NOT_FOUND));
        var pointEarned = amount.divide(ratio, RoundingMode.DOWN).intValue();

        var totalPoints = entity.getTotalPoints() + pointEarned;

        entity.setTotalPoints(totalPoints);

        benefitJpaRepository.save(entity);
    }

    @Override
    public BigDecimal discount(BigDecimal amount, Integer usedPoints, Long cardId) {

        var entity = benefitJpaRepository.findActiveByCardId(cardId)
                .orElseThrow(() -> new UnprocessableEntityException(AMOUNT_NOT_NULL));

        isNotConditional(entity.getTotalPoints() < usedPoints,
                new UnprocessableEntityException(NOT_ENOUGH_POINTS));


        Integer calculatePoints = (entity.getHasDiscount()) ?
                new BigDecimal(usedPoints).multiply(entity.getMultiplierPoints()).intValue() :
                usedPoints;

        var discount = new BigDecimal(calculatePoints).multiply(DISCOUNT_PER_POINT);

        entity.setTotalPoints(entity.getTotalPoints() - calculatePoints);
        benefitJpaRepository.save(entity);


        return amount.subtract(discount);
    }

}
