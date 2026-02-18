package com.bank.credit_card.clients.util;

import com.bank.credit_card.events.model.TransactionEvent;
import com.bank.credit_card.generic.publish.publisher.GenericEventPublisher;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.bank.credit_card.clients.constant.CardAccountConstant.*;
import static com.bank.credit_card.events.commons.TransactionType.*;

@Slf4j
@UtilityClass
public class PublishCardAccountUtility {

    public static void createEvent(GenericEventPublisher genericEventPublisher,
                                   Long cardAccountId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CREATE_CARD_ACCOUNT)
                    .entities(List.of(ENTITY_CREATE_CARD_ACCOUNT_PREFIX + cardAccountId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_CREATE_CARD_ACCOUNT, cardAccountId, e.getMessage());
        }
    }

    public static void closeEvent(GenericEventPublisher genericEventPublisher,
                                  Long cardAccountId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CLOSE_CARD_ACCOUNT)
                    .entities(List.of(ENTITY_CLOSE_CARD_ACCOUNT_PREFIX + cardAccountId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_CLOSE_CARD_ACCOUNT, cardAccountId, e.getMessage());
        }
    }

    public static void cloneEvent(GenericEventPublisher genericEventPublisher,
                                  Long originalCardAccountId,
                                  Long newCardAccountId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CLONE_CARD_ACCOUNT)
                    .entities(List.of(ENTITY_CLONE_ORIGINAL_CARD_ACCOUNT_PREFIX + originalCardAccountId,
                            ENTITY_CLONE_NEW_CARD_ACCOUNT_PREFIX + newCardAccountId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_CLONE_CARD_ACCOUNT, originalCardAccountId, newCardAccountId, e.getMessage());
        }
    }

}
