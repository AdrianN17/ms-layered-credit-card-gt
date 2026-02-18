package com.bank.credit_card.payments.util;

import com.bank.credit_card.events.model.TransactionEvent;
import com.bank.credit_card.generic.publish.publisher.GenericEventPublisher;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

import static com.bank.credit_card.events.commons.TransactionType.*;
import static com.bank.credit_card.payments.constant.PaymentConstant.*;

@Slf4j
@UtilityClass
public class PublishPaymentUtility {

    public static void createEvent(GenericEventPublisher genericEventPublisher,
                                   Long paymentId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CREATE_PAYMENT)
                    .entities(List.of(ENTITY_CREATE_PAYMENT_PREFIX + paymentId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_CREATE_PAYMENT, paymentId, e.getMessage());
        }
    }

    public static void closeEvent(GenericEventPublisher genericEventPublisher,
                                  Long paymentId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CLOSE_PAYMENT)
                    .entities(List.of(ENTITY_CLOSE_PAYMENT_PREFIX + paymentId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_CLOSE_PAYMENT, paymentId, e.getMessage());
        }
    }

    public static void approbateEvent(GenericEventPublisher genericEventPublisher,
                                      LocalDateTime targetDate,
                                      Integer count) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(APROBATE_PAYMENT)
                    .entities(List.of(APPROBATE_PAYMENT_PREFIX + targetDate,
                            APPROBATED_PAYMENT_COUNT_PREFIX + count + APPROBATED_PAYMENT_COUNT_SUFFIX))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_APROBATE_PAYMENT, targetDate, e.getMessage());
        }
    }
}
