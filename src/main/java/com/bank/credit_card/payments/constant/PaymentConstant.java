package com.bank.credit_card.payments.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentConstant {
    public static final String PAYMENT_NOT_FOUND = "Payment not found";

    public static final String ENTITY_CREATE_PAYMENT_PREFIX = "Create Payment ";
    public static final String ENTITY_CLOSE_PAYMENT_PREFIX = "Close Payment ";
    public static final String APPROBATE_PAYMENT_PREFIX = "Approbate Payments for target date ";
    public static final String APPROBATED_PAYMENT_COUNT_PREFIX = "Approbated ";
    public static final String APPROBATED_PAYMENT_COUNT_SUFFIX = " payments ";
    public static final String LOG_FAILED_CREATE_PAYMENT = "Failed to publish event for payment creation with id {}: {}";
    public static final String LOG_FAILED_CLOSE_PAYMENT = "Failed to publish event for payment closure with id {}: {}";
    public static final String LOG_FAILED_APROBATE_PAYMENT = "Failed to publish event for payment approbation for target date {}: {}";
}
