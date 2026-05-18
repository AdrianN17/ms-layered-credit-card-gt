package com.bank.credit_card.payment.service;

import com.bank.credit_card.payment.dto.PaymentRequestDto;
import com.bank.credit_card.payment.dto.PaymentResponseDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PaymentService {

    void save(PaymentRequestDto request);

    List<PaymentResponseDto> findAll(String cardId,
                                     LocalDate start,
                                     LocalDate end);

    void delete(UUID id);

    void validate(BigDecimal available,
                  BigDecimal total,
                  LocalDate startDate,
                  LocalDate endDate,
                  PaymentRequestDto request);

    PaymentResponseDto get(UUID id);
}
