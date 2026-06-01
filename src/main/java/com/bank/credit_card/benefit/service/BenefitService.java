package com.bank.credit_card.benefit.service;

import com.bank.credit_card.benefit.dto.BenefitRequestDto;

import java.math.BigDecimal;

public interface BenefitService {
    Long save(BenefitRequestDto request);
    void delete(Long cardId);
    void accumulate(BigDecimal amount, BigDecimal ratio, Long cardId);
    BigDecimal discount(BigDecimal amount, Integer usedPoints, Long cardId);
}
