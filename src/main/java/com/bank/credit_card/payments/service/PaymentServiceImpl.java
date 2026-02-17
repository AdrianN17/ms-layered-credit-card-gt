package com.bank.credit_card.payments.service;

import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.payments.dto.request.PaymentRequestDto;
import com.bank.credit_card.payments.mapper.PaymentMapper;
import com.bank.credit_card.payments.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Long create(PaymentRequestDto paymentRequestDto) {
        var payment =  paymentRepository.save(paymentMapper.toEntity(paymentRequestDto));
        return payment.getPaymentId();
    }

    @Override
    public Long close(Long paymentId) {

        var payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new CustomBadRequest("Payment not found"));
        payment.softDelete();
        paymentRepository.save(payment);

        return payment.getPaymentId();
    }
}
