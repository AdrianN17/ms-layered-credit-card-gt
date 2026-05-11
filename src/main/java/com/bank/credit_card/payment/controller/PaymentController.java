package com.bank.credit_card.payment.controller;

import com.bank.credit_card.generic.schema.response.UUID202Response;
import com.bank.credit_card.payment.delegate.PaymentDelegate;
import com.bank.credit_card.payment.schema.request.InitiatePaymentRequest;
import com.bank.credit_card.payment.schema.response.RetrievePayment200Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class PaymentController implements PaymentApi {

    private final PaymentDelegate delegate;

    @Override
    public ResponseEntity<UUID202Response> initiatePayment(Long cardId,
                                                            InitiatePaymentRequest initiatePaymentRequest,
                                                            BindingResult bindingResult) {
        return delegate.initiatePayment(cardId, initiatePaymentRequest, bindingResult);
    }

    @Override
    public ResponseEntity<UUID202Response> controlPayment(Long cardId, UUID paymentId) {
        return delegate.controlPayment(cardId, paymentId);
    }

    @Override
    public ResponseEntity<RetrievePayment200Response> retrievePayment(Long cardId,
                                                                       LocalDate dateStart,
                                                                       LocalDate dateEnd) {
        return delegate.retrievePayment(cardId, dateStart, dateEnd);
    }
}

