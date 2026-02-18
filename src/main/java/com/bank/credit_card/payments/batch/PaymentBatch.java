package com.bank.credit_card.payments.batch;

import com.bank.credit_card.payments.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.bank.credit_card.generic.util.GenericDateUtility.getCurrentLocalDate;
import static com.bank.credit_card.generic.util.GenericDateUtility.getCurrentLocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentBatch {

    private final PaymentService paymentService;

    @Scheduled(cron = "0 0 3 * * *")
    public void executeBatch() {
        log.info("Starting payment batch execution");
        var dateTime = getCurrentLocalDateTime();
        var date = getCurrentLocalDate();
        paymentService.approbate(date, dateTime);
        log.info("Finished payment batch execution");

    }
}
