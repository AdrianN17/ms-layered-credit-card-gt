package com.bank.credit_card.consumptions.util;

import com.bank.credit_card.clients.commons.CategoryCard;
import com.bank.credit_card.consumptions.service.business.PointBusinessImpl;
import com.bank.credit_card.events.model.BonusPoints;
import com.bank.credit_card.events.model.TransactionEvent;
import com.bank.credit_card.generic.publish.publisher.GenericEventPublisher;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.bank.credit_card.consumptions.constant.ConsumptionConstant.*;
import static com.bank.credit_card.events.commons.TransactionType.*;

@Slf4j
@UtilityClass
public class PublishConsumptionUtility {

    public static void createEvent(GenericEventPublisher genericEventPublisher,
                                   Long consumptionId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CREATE_CONSUMPTION)
                    .entities(List.of(ENTITY_CREATE_CONSUMPTION_PREFIX + consumptionId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_CREATE_CONSUMPTION, consumptionId, e.getMessage());
        }
    }

    public static void closeEvent(GenericEventPublisher genericEventPublisher,
                                  Long consumptionId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CLOSE_CONSUMPTION)
                    .entities(List.of(ENTITY_CLOSE_CONSUMPTION_PREFIX + consumptionId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_CLOSE_CONSUMPTION, consumptionId, e.getMessage());
        }
    }

    public static void publishPoints(GenericEventPublisher genericEventPublisher,
                                     PointBusinessImpl defaultPointsPolicy,
                                     Optional<Short> categoryId,
                                     Long consumptionId,
                                     Long cardId,
                                     BigDecimal amount) {
        try {
            if (categoryId.isPresent()) {
                var points = defaultPointsPolicy.calculatePoints(amount,
                        CategoryCard.fromValue(categoryId.get()));

                BonusPoints bonusPoints = BonusPoints.builder()
                        .consumptionId(consumptionId)
                        .cardId(cardId)
                        .pointsEarned(points)
                        .build();

                genericEventPublisher.publish(bonusPoints);
                pointsEvent(genericEventPublisher, consumptionId, bonusPoints.eventId());
            }
        } catch (Exception e) {
            log.error(LOG_FAILED_POINTS, consumptionId, e.getMessage());
        }
    }

    public static void approbateEvent(GenericEventPublisher genericEventPublisher,
                                      LocalDateTime targetDate,
                                      Integer count) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(APROBATE_CONSUMPTION)
                    .entities(List.of(APPROBATE_CONSUMPTION_PREFIX + targetDate,
                            APPROBATED_CONSUMPTION_COUNT_PREFIX + count + APPROBATED_CONSUMPTION_COUNT_SUFFIX))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_APROBATE_CONSUMPTION, targetDate, e.getMessage());
        }
    }

    public static void splitEvent(GenericEventPublisher genericEventPublisher,
                                  Long consumptionId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(SPLIT_CONSUMPTION)
                    .entities(List.of(SPLIT_CONSUMPTION_PREFIX + consumptionId))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_SPLIT_CONSUMPTION, consumptionId, e.getMessage());
        }
    }

    public static void pointsEvent(GenericEventPublisher genericEventPublisher,
                                   Long consumptionId,
                                   UUID pointsEventId) {
        try {
            genericEventPublisher.publish(TransactionEvent.builder()
                    .transactionType(CREATE_POINTS)
                    .entities(List.of(CONSUMPTION_PREFIX + consumptionId, POINTS_PREFIX + pointsEventId.toString()))
                    .build());
        } catch (Exception e) {
            log.error(LOG_FAILED_POINTS, consumptionId, e.getMessage());
        }
    }


}
