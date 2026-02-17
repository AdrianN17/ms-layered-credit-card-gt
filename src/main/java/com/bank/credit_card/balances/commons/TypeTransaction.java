package com.bank.credit_card.balances.commons;

public enum TypeTransaction {
    CONSUMPTION("Consumption"),
    PAYMENT("Payment");

    private final String value;

    TypeTransaction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
