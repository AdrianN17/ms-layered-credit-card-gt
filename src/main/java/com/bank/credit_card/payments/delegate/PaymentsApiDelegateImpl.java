package com.bank.credit_card.payments.delegate;

import com.bank.credit_card.generic.schema.response.Tracking;
import com.bank.credit_card.payments.mapper.PaymentMapper;
import com.bank.credit_card.payments.schema.request.PaymentRequest;
import com.bank.credit_card.payments.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import static com.bank.credit_card.generic.util.GenericDateUtility.getCurrentLocalDateTime;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownBadRequest;
import static com.bank.credit_card.generic.util.GenericResponsesUtility.generateTracking;

@Component
@AllArgsConstructor
public class PaymentsApiDelegateImpl implements PaymentsApiDelegate {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @Override
    public ResponseEntity<Tracking> closePayment(Long paymentId) {
        paymentService.close(paymentId);
        return generateTracking();
    }

    @Override
    public ResponseEntity<Tracking> createPayment(Long cardId, PaymentRequest paymentRequest, BindingResult bindingResult) {
        thrownBadRequest(bindingResult);
        paymentService.create(paymentMapper.toDto(paymentRequest, cardId, getCurrentLocalDateTime()));
        return generateTracking();
    }
}
