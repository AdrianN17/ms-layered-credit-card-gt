package com.bank.credit_card.payments.service;

import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.payments.dto.PaymentDto;
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
    public void createPayment(PaymentDto paymentDto) {
        paymentRepository.save(paymentMapper.toEntity(paymentDto));
    }

    @Override
    public void closePayment(Long paymentId) {

        paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new CustomBadRequest("Payment not found"))
                .softDelete();
    }
}
