package com.bank.credit_card.consumptions.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConsumptionConstant {
    public static final String CONSUMPTION_NOT_FOUND = "Consumption not found";
    public static final String TAX_ASSOCIATED_CARD_ACCOUNT_NOT_FOUND = "Tax associated with this card account not found";

    public static final String ENTITY_CREATE_CONSUMPTION_PREFIX = "Create Consumption ";
    public static final String ENTITY_CLOSE_CONSUMPTION_PREFIX = "Close Consumption ";
    public static final String APPROBATE_CONSUMPTION_PREFIX = "Approbate Consumption for target date ";
    public static final String APPROBATED_CONSUMPTION_COUNT_PREFIX = "Approbated ";
    public static final String APPROBATED_CONSUMPTION_COUNT_SUFFIX = " consumptions ";
    public static final String SPLIT_CONSUMPTION_PREFIX = "Split Consumption ";
    public static final String CONSUMPTION_PREFIX = "Consumption ";
    public static final String POINTS_PREFIX = "Points ";
    public static final String LOG_FAILED_CREATE_CONSUMPTION = "Failed to publish event for consumption creation with id {}: {}";
    public static final String LOG_FAILED_CLOSE_CONSUMPTION = "Failed to publish event for consumption closure with id {}: {}";
    public static final String LOG_FAILED_APROBATE_CONSUMPTION = "Failed to publish event for consumption approbation for target date {}: {}";
    public static final String LOG_FAILED_SPLIT_CONSUMPTION = "Failed to publish event for consumption split with id {}: {}";
    public static final String LOG_FAILED_POINTS = "Error publishing points for consumptionId {}: {}";
}
