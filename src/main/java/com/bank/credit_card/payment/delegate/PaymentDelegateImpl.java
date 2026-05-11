package com.bank.credit_card.payment.delegate;

import com.bank.credit_card.generic.schema.response.UUID202Response;
import com.bank.credit_card.payment.schema.request.InitiatePaymentRequest;
import com.bank.credit_card.payment.schema.response.RetrievePayment200Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PaymentDelegateImpl implements PaymentDelegate {

    @Override
    public ResponseEntity<UUID202Response> initiatePayment(Long cardId,
                                                            InitiatePaymentRequest initiatePaymentRequest,
                                                            BindingResult bindingResult) {
        return null;
    }

    @Override
    public ResponseEntity<UUID202Response> controlPayment(Long cardId, UUID paymentId) {
        return null;
    }

    @Override
    public ResponseEntity<RetrievePayment200Response> retrievePayment(Long cardId,
                                                                       LocalDate dateStart,
                                                                       LocalDate dateEnd) {
        return null;
    }
}

