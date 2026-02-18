package com.bank.credit_card.clients.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CardAccountConstant {
    public static final String CARD_ACCOUNT_NOT_FOUND = "Card account not found ";
    public static final String CARD_ACCOUNT_OF_CARD_NOT_FOUND = "Card account of this card not found ";

    public static final String ENTITY_CREATE_CARD_ACCOUNT_PREFIX = "Create Card Account ";
    public static final String ENTITY_CLOSE_CARD_ACCOUNT_PREFIX = "Close Card Account ";
    public static final String LOG_FAILED_CREATE_CARD_ACCOUNT = "Failed to publish event for card account creation with id {}: {}";
    public static final String LOG_FAILED_CLOSE_CARD_ACCOUNT = "Failed to publish event for card account closure with id {}: {}";

    public static final String ENTITY_CLONE_ORIGINAL_CARD_ACCOUNT_PREFIX = "Clone Original Card Account ";
    public static final String ENTITY_CLONE_NEW_CARD_ACCOUNT_PREFIX = "Clone New Card Account ";
    public static final String LOG_FAILED_CLONE_CARD_ACCOUNT = "Failed to publish event for card account cloning from original id {} to new id {}: {}";
}
