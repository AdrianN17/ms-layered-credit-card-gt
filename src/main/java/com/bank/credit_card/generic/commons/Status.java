package com.bank.credit_card.generic.commons;

import java.util.Arrays;
import java.util.Optional;

import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownBadRequest;

public enum Status {
    INACTIVE("INACTIVE", 0),
    ACTIVE("ACTIVE", 1);

    private final String code;
    private final int value;

    Status(String code, int value) {
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

    public static Status fromCode(String code) {
        return ofCode(code).orElseThrow(() -> thrownBadRequest("Unknown Status code: " + code));
    }

    public static Status fromValue(int value) {
        return ofValue(value).orElseThrow(() -> thrownBadRequest("Unknown Status value: " + value));
    }

    public static Optional<Status> ofCode(String code) {
        if (code == null) return Optional.empty();
        String trimmed = code.trim();
        return Arrays.stream(values())
                .filter(s -> s.code.equalsIgnoreCase(trimmed))
                .findFirst();
    }

    public static Optional<Status> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(s -> s.value == value)
                .findFirst();
    }

    public static boolean isValidCode(String code) {
        return ofCode(code).isPresent();
    }

    public static boolean isValidValue(Integer value) {
        return ofValue(value).isPresent();
    }
}
