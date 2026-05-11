package com.bank.credit_card.payment.delegate;

import com.bank.credit_card.generic.schema.response.UUID202Response;
import com.bank.credit_card.payment.schema.request.InitiatePaymentRequest;
import com.bank.credit_card.payment.schema.response.RetrievePayment200Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.UUID;

public interface PaymentDelegate {

    ResponseEntity<UUID202Response> initiatePayment(Long cardId,
                                                     InitiatePaymentRequest initiatePaymentRequest,
                                                     BindingResult bindingResult);

    ResponseEntity<UUID202Response> controlPayment(Long cardId, UUID paymentId);

    ResponseEntity<RetrievePayment200Response> retrievePayment(Long cardId,
                                                                LocalDate dateStart,
                                                                LocalDate dateEnd);
}

