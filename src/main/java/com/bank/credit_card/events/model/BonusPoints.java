package com.bank.credit_card.events.model;

import com.bank.credit_card.generic.publish.model.GenericEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.bank.credit_card.generic.util.GenericDateUtility.getCurrentLocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public final class BonusPoints implements GenericEvent {

    private final UUID eventId;
    private final LocalDateTime occurredOn;
    private final Long consumptionId;
    private final Long cardId;
    private final int pointsEarned;


    @Override
    public UUID eventId() {
        return UUID.randomUUID();
    }

    @Override
    public LocalDateTime occurredOn() {
        return getCurrentLocalDateTime();
    }
}
