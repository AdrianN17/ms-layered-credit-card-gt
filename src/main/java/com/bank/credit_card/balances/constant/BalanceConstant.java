package com.bank.credit_card.balances.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BalanceConstant {
    public final String BALANCE_NOT_FOUND = "Balance not found";
    public final String BALANCE_OF_CARD_NOT_FOUND = "Balance of this Card not found";
    public final String CARD_ACCOUNT_DATA_ID_NOT_FOUND = "Card Account data of card with id not found";

    public static final String ENTITY_CREATE_BALANCE_PREFIX = "Create Balance ";
    public static final String ENTITY_CLOSE_BALANCE_PREFIX = "Close Balance ";
    public static final String LOG_FAILED_CREATE_BALANCE = "Failed to publish event for balance creation with id {}: {}";
    public static final String LOG_FAILED_CLOSE_BALANCE = "Failed to publish event for balance closure with id {}: {}";
}
