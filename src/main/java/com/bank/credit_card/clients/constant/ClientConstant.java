package com.bank.credit_card.clients.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientConstant {
    public static final String CLIENT_NOT_FOUND = "Client not found";

    public static final String ENTITY_CREATE_CLIENT_PREFIX = "Create Client ";
    public static final String ENTITY_CLOSE_CLIENT_PREFIX = "Close Client ";
    public static final String LOG_FAILED_CREATE_CLIENT = "Failed to publish event for client creation with id {}: {}";
    public static final String LOG_FAILED_CLOSE_CLIENT = "Failed to publish event for client closure with id {}: {}";
}
