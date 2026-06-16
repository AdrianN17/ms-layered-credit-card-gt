package com.bank.credit_card.payment.service;

import com.bank.credit_card.generic.service.GenericService;
import com.bank.credit_card.generic.service.GenericTransactionService;
import com.bank.credit_card.payment.dto.PaymentRequestDto;
import com.bank.credit_card.payment.dto.PaymentResponseDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface PaymentService extends GenericService<PaymentRequestDto, UUID>,
        GenericTransactionService<String, UUID, PaymentResponseDto> {

    void validate(BigDecimal available,
                  BigDecimal total,
                  LocalDate startDate,
                  LocalDate endDate,
                  PaymentRequestDto request);
}
