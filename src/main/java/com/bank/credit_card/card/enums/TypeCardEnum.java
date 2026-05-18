package com.bank.credit_card.card.enums;

import com.bank.credit_card.generic.enums.ValuedEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum TypeCardEnum implements ValuedEnum {
    VISA("VISA", 1),
    MASTERCARD("MASTERCARD", 2);

    private final String code;
    private final int value;



    public static Optional<TypeCardEnum> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }
}
