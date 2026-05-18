package com.bank.credit_card.payment.enums;

import com.bank.credit_card.generic.enums.ValuedEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum ChannelPaymentEnum implements ValuedEnum {
    WEB("WEB", 1),
    APP("APP", 2);

    private final String code;
    private final int value;



    public static Optional<ChannelPaymentEnum> ofValue(Integer value) {
        if (value == null) return Optional.empty();
        return Arrays.stream(values())
                .filter(c -> c.value == value)
                .findFirst();
    }

    public static ChannelPaymentEnum ofCode(String code) {
        return Arrays.stream(values())
                .filter(c -> c.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid ChannelPaymentEnum code: " + code));
    }
}
