package com.bank.credit_card.clients.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CardConstant {
    public static final String CARD_NOT_FOUND = "Card not found";

    public static final String ENTITY_CREATE_CARD_PREFIX = "Create Card ";
    public static final String ENTITY_CLOSE_CARD_PREFIX = "Close Card ";
    public static final String LOG_FAILED_CREATE_CARD = "Failed to publish event for card creation with id {}: {}";
    public static final String LOG_FAILED_CLOSE_CARD = "Failed to publish event for card closure with id {}: {}";

    public static final String ENTITY_CLONE_ORIGINAL_CARD_PREFIX = "Clone Original Card ";
    public static final String ENTITY_CLONE_NEW_CARD_PREFIX = "Clone New Card ";
    public static final String LOG_FAILED_CLONE_CARD = "Failed to publish event for card cloning from original id {} to new id {}: {}";
}
