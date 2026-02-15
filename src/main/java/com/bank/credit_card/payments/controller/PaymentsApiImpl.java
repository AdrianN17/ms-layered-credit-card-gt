package com.bank.credit_card.payments.controller;

import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.generic.schema.response.Tracking;
import com.bank.credit_card.payments.mapper.PaymentMapper;
import com.bank.credit_card.payments.schema.request.PaymentRequest;
import com.bank.credit_card.payments.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PaymentsApiImpl implements PaymentsApi{

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @Override
    public ResponseEntity<Tracking> closePayment(Long paymentId) {
        paymentService.closePayment(paymentId);
        return null;
    }

    @Override
    public ResponseEntity<Tracking> createPayment(Long cardId, PaymentRequest paymentRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new CustomBadRequest(bindingResult);

        paymentService.createPayment(paymentMapper.toDto(paymentRequest, cardId));
        return null;
    }
}
