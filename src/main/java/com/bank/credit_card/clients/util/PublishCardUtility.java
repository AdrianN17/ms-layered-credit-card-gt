package com.bank.credit_card.clients.util;

import com.bank.credit_card.events.model.TransactionEvent;
import com.bank.credit_card.generic.publish.publisher.GenericEventPublisher;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.bank.credit_card.clients.constant.CardConstant.*;
import static com.bank.credit_card.events.commons.TransactionType.*;

@Slf4j
@UtilityClass
public class PublishCardUtility {

    public static void createEvent(GenericEventPublisher genericEventPublisher,
                                   Long clientId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CREATE_CARD)
                    .entities(List.of(ENTITY_CREATE_CARD_PREFIX + clientId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_CREATE_CARD, clientId, e.getMessage());
        }
    }

    public static void closeEvent(GenericEventPublisher genericEventPublisher,
                                  Long clientId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CLOSE_CARD)
                    .entities(List.of(ENTITY_CLOSE_CARD_PREFIX + clientId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_CLOSE_CARD, clientId, e.getMessage());
        }
    }

    public static void cloneEvent(GenericEventPublisher genericEventPublisher,
                                  Long originalCardId,
                                  Long newCardId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CLONE_CARD)
                    .entities(List.of(ENTITY_CLONE_ORIGINAL_CARD_PREFIX + originalCardId,
                            ENTITY_CLONE_NEW_CARD_PREFIX + newCardId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_CLONE_CARD, originalCardId, newCardId, e.getMessage());
        }
    }


}
