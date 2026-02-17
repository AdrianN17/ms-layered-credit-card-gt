package com.bank.credit_card.payments.commons;

import java.util.Arrays;
import java.util.Optional;

import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownBadRequest;

public enum ChannelPayment {
    WEB("WEB", 1),
    APP("APP", 2);

    private final String code;
    private final int value;

    ChannelPayment(String code, int value) {
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

    public static Optional<ChannelPayment> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }

    public static ChannelPayment fromValue(int value) {
        return ofValue(value).orElseThrow(() -> thrownBadRequest("Unknown ChannelPayment value: " + value));
    }

    public static Optional<ChannelPayment> ofCode(String code) {
        if (code == null) return Optional.empty();
        String trimmed = code.trim();
        return Arrays.stream(values())
                .filter(c -> c.code.equalsIgnoreCase(trimmed))
                .findFirst();
    }

    public static ChannelPayment fromCode(String code) {
        return ofCode(code).orElseThrow(() -> thrownBadRequest("Unknown ChannelPayment code: " + code));
    }

    public static boolean isValidValue(Integer value) {
        return ofValue(value).isPresent();
    }

    public static boolean isValidCode(String name) {
        return ofCode(name).isPresent();
    }
}
