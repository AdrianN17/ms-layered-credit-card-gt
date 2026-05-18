package com.bank.credit_card.generic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum CurrencyEnum implements ValuedEnum {
    PEN("PEN", 1),
    USD("USD", 2);

    private final String code;
    private final int value;

    public static Optional<CurrencyEnum> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }
}
