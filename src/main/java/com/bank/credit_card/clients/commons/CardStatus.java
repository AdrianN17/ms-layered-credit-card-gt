package com.bank.credit_card.clients.commons;

import java.util.Arrays;
import java.util.Optional;

import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownBadRequest;

public enum CardStatus {
    OPERATIVE("OPERATIVA", 1),
    OVERCHARGE("SOBREGIRADA", 2),
    IN_DEBT("DEUDOR", 3);

    private final String code;
    private final int value;

    CardStatus(String code, int value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return code;
    }

    public static Optional<CardStatus> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }

    public static CardStatus fromValue(int value) {
        return ofValue(value).orElseThrow(() -> thrownBadRequest("Unknown CardStatus value: " + value));
    }

    public static Optional<CardStatus> ofCode(String code) {
        if (code == null) return Optional.empty();
        String trimmed = code.trim();
        return Arrays.stream(values())
                .filter(c -> c.code.equalsIgnoreCase(trimmed))
                .findFirst();
    }

    public static CardStatus fromCode(String code) {
        return ofCode(code).orElseThrow(() -> thrownBadRequest("Unknown CardStatus code: " + code));
    }

    public static boolean isValidValue(Integer value) {
        return ofValue(value).isPresent();
    }

    public static boolean isValidCode(String code) {
        return ofCode(code).isPresent();
    }
}
