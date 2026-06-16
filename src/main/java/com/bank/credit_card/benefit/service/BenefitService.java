package com.bank.credit_card.benefit.service;

import com.bank.credit_card.benefit.dto.BenefitRequestDto;
import com.bank.credit_card.generic.service.GenericService;

import java.math.BigDecimal;

public interface BenefitService extends GenericService<BenefitRequestDto, Long> {
    void accumulate(BigDecimal amount, BigDecimal ratio, Long cardId);
    BigDecimal discount(BigDecimal amount, Integer usedPoints, Long cardId);
}
