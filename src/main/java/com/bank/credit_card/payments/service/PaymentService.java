package com.bank.credit_card.payments.service;

import com.bank.credit_card.payments.dto.PaymentDto;

public interface PaymentService {
    void createPayment(PaymentDto paymentDto);

    void closePayment(Long paymentId);
}
