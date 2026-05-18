package com.bank.credit_card.balance.exception;

public interface BalanceErrorMessage {
    String BALANCE_NOT_FOUND              = "Balance not found";
    String BALANCE_NOT_SAVED              = "Balance not saved";
    String AMOUNT_EXCEED_CREDIT_LIMIT     = "Amount exceeds credit limit";
    String PAYMENT_CANNOT_BE_NULL         = "Payment amount cannot be null";
    String PAYMENT_CATEGORY_EXCEED_LIKE   = "Payment exceeds available balance by: ";
    String BALANCE_OVERCHARGED            = "Balance is overcharged";
    String CARD_STATUS_UPDATE_FAILED      = "Error updating card status for cardId: %s";
}
