package com.bank.credit_card.payments.delegate;

import com.bank.credit_card.generic.schema.response.Tracking;
import com.bank.credit_card.payments.schema.request.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface PaymentsApiDelegate {
    ResponseEntity<Tracking> closePayment(Long paymentId);
    ResponseEntity<Tracking> createPayment(Long cardId, PaymentRequest paymentRequest, BindingResult bindingResult);
}
