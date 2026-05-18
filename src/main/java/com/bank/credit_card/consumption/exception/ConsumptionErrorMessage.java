package com.bank.credit_card.consumption.exception;

public interface ConsumptionErrorMessage {
    String CONSUMPTION_AMOUNT_CANNOT_BE_NULL = "The consumption amount cannot be null.";
    String CONSUMPTION_CURRENCY_CANNOT_BE_NULL = "The consumption currency cannot be null.";
    String CARD_ID_NOT_NULL = "El identificador de la tarjeta del pay no puede ser nulo";
    String CONSUMPTION_IS_STILL_IN_APPROBATION = "The consumption is still in approbation.";
    String TAX_AMOUNT_CANNOT_BE_NULL = "The tax amount cannot be null.";
    String QUANTITY_CANNOT_BE_NULL = "The quantity cannot be null.";
    String SELLER_NAME_CANNOT_BE_NULL = "The seller name cannot be null.";
    String CONSUMPTION_SPLIT = " - SPLIT";
    String CONSUMPTION_NOT_FOUND = "Consumption not found";
    String CONSUMPTION_DATE_NOT_NULL = "Consumption date cannot be null";
    String CONSUMPTION_APPROBATION_DATE_NOT_NULL = "Consumption approbation date cannot be null";
}
