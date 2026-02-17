package com.bank.credit_card.payments.controller;

import com.bank.credit_card.generic.schema.response.Tracking;
import com.bank.credit_card.payments.delegate.PaymentsApiDelegate;
import com.bank.credit_card.payments.schema.request.PaymentRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PaymentsApiImpl implements PaymentsApi {

    private final PaymentsApiDelegate paymentsApiDelegate;

    @Override
    public ResponseEntity<Tracking> closePayment(Long paymentId) {
        return paymentsApiDelegate.closePayment(paymentId);
    }

    @Override
    public ResponseEntity<Tracking> createPayment(Long cardId, PaymentRequest paymentRequest, BindingResult bindingResult) {
        return paymentsApiDelegate.createPayment(cardId, paymentRequest, bindingResult);
    }
}
