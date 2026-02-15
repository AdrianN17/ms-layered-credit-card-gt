package com.bank.credit_card.payments.commons;

import java.util.Arrays;
import java.util.Optional;

public enum CategoryPayment {
    MES("MES", 1),
    ADELANTADO("ADELANTADO", 2),
    TOTAL("TOTAL", 3);

    private final String code;
    private final int value;

    CategoryPayment(String code, int value) {
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

    public static Optional<CategoryPayment> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }

    public static CategoryPayment fromValue(int value) {
        return ofValue(value).orElseThrow(() -> new IllegalArgumentException("Unknown CategoryPayment value: " + value));
    }

    public static Optional<CategoryPayment> ofCode(String code) {
        if (code == null) return Optional.empty();
        String trimmed = code.trim();
        return Arrays.stream(values())
                .filter(c -> c.code.equalsIgnoreCase(trimmed))
                .findFirst();
    }

    public static CategoryPayment fromCode(String code) {
        return ofCode(code).orElseThrow(() -> new IllegalArgumentException("Unknown CategoryPayment code: " + code));
    }

    public static boolean isValidValue(Integer value) {
        return ofValue(value).isPresent();
    }

    public static boolean isValidCode(String code) {
        return ofCode(code).isPresent();
    }
}
