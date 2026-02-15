package com.bank.credit_card.clients.commons;

import java.util.Arrays;
import java.util.Optional;

public enum CategoryCard {
    NORMAL("NORMAL", 1),
    SILVER("SILVER", 2),
    GOLD("GOLD", 3),
    PLATINUM("PLATINUM", 4),
    BLACK("BLACK", 5),
    SIGNATURE("SIGNATURE", 6),
    INFINITY("INFINITY", 7);

    private final String code;
    private final int value;

    CategoryCard(String code, int value) {
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

    public static Optional<CategoryCard> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }

    public static CategoryCard fromValue(int value) {
        return ofValue(value).orElseThrow(() -> new IllegalArgumentException("Unknown CategoryCard value: " + value));
    }

    public static Optional<CategoryCard> ofCode(String code) {
        if (code == null) return Optional.empty();
        String trimmed = code.trim();
        return Arrays.stream(values())
                .filter(c -> c.code.equalsIgnoreCase(trimmed))
                .findFirst();
    }

    public static CategoryCard fromCode(String code) {
        return ofCode(code).orElseThrow(() -> new IllegalArgumentException("Unknown CategoryCard code: " + code));
    }

    public static boolean isValidValue(Integer value) {
        return ofValue(value).isPresent();
    }

    public static boolean isValidCode(String code) {
        return ofCode(code).isPresent();
    }
}
