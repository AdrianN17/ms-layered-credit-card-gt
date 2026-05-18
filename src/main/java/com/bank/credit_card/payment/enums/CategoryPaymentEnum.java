package com.bank.credit_card.payment.enums;

import com.bank.credit_card.generic.enums.ValuedEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum CategoryPaymentEnum implements ValuedEnum {

    NORMAL("NORMAL", 1),
    TOTAL("TOTAL", 2),
    MINIMO("MINIMO", 3),
    ADELANTADO("ADELANTADO", 4);

    private final String code;
    private final int value;



    public static Optional<CategoryPaymentEnum> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }
}
