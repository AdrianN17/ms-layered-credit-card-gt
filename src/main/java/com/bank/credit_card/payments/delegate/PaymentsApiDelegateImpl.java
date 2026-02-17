package com.bank.credit_card.payments.delegate;

import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.generic.schema.response.Tracking;
import com.bank.credit_card.payments.mapper.PaymentMapper;
import com.bank.credit_card.payments.schema.request.PaymentRequest;
import com.bank.credit_card.payments.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@AllArgsConstructor
public class PaymentsApiDelegateImpl implements PaymentsApiDelegate {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @Override
    public ResponseEntity<Tracking> closePayment(Long paymentId) {
        paymentService.close(paymentId);
        return null;
    }

    @Override
    public ResponseEntity<Tracking> createPayment(Long cardId, PaymentRequest paymentRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new CustomBadRequest(bindingResult);

        paymentService.create(paymentMapper.toDto(paymentRequest, cardId));
        return null;
    }
}
