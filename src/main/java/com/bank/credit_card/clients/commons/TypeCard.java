package com.bank.credit_card.clients.commons;

import java.util.Arrays;
import java.util.Optional;

import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownBadRequest;

public enum TypeCard {
    VISA("VISA", 1),
    MASTERCARD("MASTERCARD", 2);

    private final String code;
    private final int value;

    TypeCard(String code, int value) {
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

    public static Optional<TypeCard> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }

    public static TypeCard fromValue(int value) {
        return ofValue(value).orElseThrow(() -> thrownBadRequest("Unknown TypeCard value: " + value));
    }

    // Unified method: ofCode (compares against the 'code' field)
    public static Optional<TypeCard> ofCode(String code) {
        if (code == null) return Optional.empty();
        String trimmed = code.trim();
        return Arrays.stream(values())
                .filter(c -> c.code.equalsIgnoreCase(trimmed))
                .findFirst();
    }

    public static TypeCard fromCode(String code) {
        return ofCode(code).orElseThrow(() -> thrownBadRequest("Unknown TypeCard code: " + code));
    }

    public static boolean isValidValue(Integer value) {
        return ofValue(value).isPresent();
    }

    public static boolean isValidCode(String code) {
        return ofCode(code).isPresent();
    }
}
