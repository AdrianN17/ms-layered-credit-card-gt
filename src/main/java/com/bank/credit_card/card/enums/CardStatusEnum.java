package com.bank.credit_card.card.enums;

import com.bank.credit_card.generic.enums.ValuedEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum CardStatusEnum implements ValuedEnum {
    OPERATIVE("OPERATIVA", 1),
    OVERCHARGE("SOBREGIRADA", 2),
    IN_DEBT("DEUDOR", 3);

    private final String code;
    private final int value;

    public static Optional<CardStatusEnum> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }
}
