package com.bank.credit_card.generic.commons;

import java.util.Arrays;
import java.util.Optional;

import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownBadRequest;

public enum Currency {
    PEN("PEN", 1),
    USD("USD", 2);

    private final String code;
    private final int value;

    Currency(String code, int value) {
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

    public static Currency fromCode(String code) {
        return ofCode(code).orElseThrow(() -> thrownBadRequest("Unknown Currency code: " + code));
    }

    public static Currency fromValue(int value) {
        return ofValue(value).orElseThrow(() -> thrownBadRequest("Unknown Currency value: " + value));
    }

    public static Optional<Currency> ofCode(String code) {
        if (code == null) return Optional.empty();
        String trimmed = code.trim();
        return Arrays.stream(values())
                .filter(c -> c.code.equalsIgnoreCase(trimmed))
                .findFirst();
    }

    public static Optional<Currency> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }

    public static boolean isValidCode(String code) {
        return ofCode(code).isPresent();
    }

    public static boolean isValidValue(Integer value) {
        return ofValue(value).isPresent();
    }
}
