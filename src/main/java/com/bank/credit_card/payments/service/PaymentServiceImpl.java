package com.bank.credit_card.payments.service;

import com.bank.credit_card.generic.publish.publisher.GenericEventPublisher;
import com.bank.credit_card.payments.dto.request.PaymentRequestDto;
import com.bank.credit_card.payments.mapper.PaymentMapper;
import com.bank.credit_card.payments.repository.PaymentRepository;
import com.bank.credit_card.payments.repository.procedure.PaymentRepositoryCustom;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownNotFound;
import static com.bank.credit_card.payments.constant.PaymentConstant.PAYMENT_NOT_FOUND;
import static com.bank.credit_card.payments.util.PublishPaymentUtility.*;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentRepositoryCustom paymentRepositoryCustom;
    private final GenericEventPublisher genericEventPublisher;

    @Override
    public Long create(PaymentRequestDto paymentRequestDto) {
        var payment = paymentRepository.save(paymentMapper.toEntity(paymentRequestDto));
        createEvent(genericEventPublisher,
                payment.getPaymentId());
        return payment.getPaymentId();
    }


    @Override
    public Long close(Long paymentId) {

        var payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        thrownNotFound(PAYMENT_NOT_FOUND));
        payment.softDelete();
        paymentRepository.save(payment);

        closeEvent(genericEventPublisher,
                paymentId);

        return payment.getPaymentId();
    }

    @Override
    public void approbate(LocalDate targetDate, LocalDateTime approbationDate) {
        var count = paymentRepositoryCustom.approbate(targetDate, approbationDate);
        approbateEvent(genericEventPublisher,
                approbationDate,
                count);
        log.info("Approbated {} payments for target date {}", count, targetDate);
    }
}
