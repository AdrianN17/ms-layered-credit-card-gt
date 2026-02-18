package com.bank.credit_card.clients.util;

import com.bank.credit_card.events.model.TransactionEvent;
import com.bank.credit_card.generic.publish.publisher.GenericEventPublisher;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.bank.credit_card.clients.constant.ClientConstant.*;
import static com.bank.credit_card.events.commons.TransactionType.CLOSE_BALANCE;
import static com.bank.credit_card.events.commons.TransactionType.CREATE_BALANCE;

@Slf4j
@UtilityClass
public class PublishClientUtility {

    public static void createEvent(GenericEventPublisher genericEventPublisher,
                                   Long clientId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CREATE_BALANCE)
                    .entities(List.of(ENTITY_CREATE_CLIENT_PREFIX + clientId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_CREATE_CLIENT, clientId, e.getMessage());
        }
    }

    public static void closeEvent(GenericEventPublisher genericEventPublisher,
                                  Long clientId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CLOSE_BALANCE)
                    .entities(List.of(ENTITY_CLOSE_CLIENT_PREFIX + clientId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_CLOSE_CLIENT, clientId, e.getMessage());
        }
    }


}
