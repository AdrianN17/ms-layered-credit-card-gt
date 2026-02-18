package com.bank.credit_card.generic.publish.model;

import java.time.LocalDateTime;
import java.util.UUID;

public interface GenericEvent {

    UUID eventId();

    LocalDateTime occurredOn();
}
