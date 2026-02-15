package com.bank.credit_card.clients.commons;

import java.util.Arrays;
import java.util.Optional;

public enum DocumentType {
    DNI("DNI", 1),
    CE("CE", 2),
    RUC("RUC", 3),
    PASSPORT("PASSPORT", 4);

    private final String code;
    private final int value;

    DocumentType(String code, int value) {
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

    public static Optional<DocumentType> ofCode(String code) {
        if (code == null) return Optional.empty();
        String trimmed = code.trim();
        return Arrays.stream(values())
                .filter(dt -> dt.code.equalsIgnoreCase(trimmed))
                .findFirst();
    }

    public static Optional<DocumentType> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(dt -> dt.value == value)
                .findFirst();
    }

    public static DocumentType fromCode(String code) {
        return ofCode(code).orElseThrow(() -> new IllegalArgumentException("Unknown DocumentType code: " + code));
    }

    public static DocumentType fromValue(int value) {
        return ofValue(value).orElseThrow(() -> new IllegalArgumentException("Unknown DocumentType value: " + value));
    }

    public static boolean isValidCode(String code) {
        return ofCode(code).isPresent();
    }

    public static boolean isValidValue(Integer value) {
        return ofValue(value).isPresent();
    }
}
