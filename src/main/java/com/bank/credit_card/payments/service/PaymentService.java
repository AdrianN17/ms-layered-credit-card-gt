package com.bank.credit_card.payments.service;

import com.bank.credit_card.generic.service.GenericService;
import com.bank.credit_card.payments.dto.request.PaymentRequestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PaymentService extends GenericService<PaymentRequestDto, Long> {
    void approbate(LocalDate targetDate,
                   LocalDateTime approbationDate);
}
