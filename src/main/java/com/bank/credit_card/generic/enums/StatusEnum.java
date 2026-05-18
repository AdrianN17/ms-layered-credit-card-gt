package com.bank.credit_card.generic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum StatusEnum implements ValuedEnum {
    INACTIVE("INACTIVE", 0),
    ACTIVE("ACTIVE", 1);

    private final String code;
    private final int value;

    public static Optional<StatusEnum> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }

    public static StatusEnum ofCode(String code) {
        return Arrays.stream(values())
                .filter(c -> c.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid StatusEnum code: " + code));
    }
}