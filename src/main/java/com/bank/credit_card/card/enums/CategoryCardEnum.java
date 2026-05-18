package com.bank.credit_card.card.enums;

import com.bank.credit_card.generic.enums.ValuedEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum CategoryCardEnum implements ValuedEnum {
    NORMAL("NORMAL", 1),
    SILVER("SILVER", 2),
    GOLD("GOLD", 3),
    PLATINUM("PLATINUM", 4),
    BLACK("BLACK", 5),
    SIGNATURE("SIGNATURE", 6),
    INFINITY("INFINITY", 7);

    private final String code;
    private final int value;



    public static Optional<CategoryCardEnum> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }
}
