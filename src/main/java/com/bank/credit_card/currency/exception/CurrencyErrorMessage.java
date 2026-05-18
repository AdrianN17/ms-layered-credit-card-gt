package com.bank.credit_card.currency.exception;

public interface CurrencyErrorMessage {
    String CURRENCY_NOT_FOUND    = "Currency not found: %s";
    String CURRENCY_WS_ERROR     = "Error calling currency WS for: %s";
}
