package com.bank.credit_card.generic.util;

import java.time.LocalDate;

import static java.util.Objects.isNull;

public class Validation {
    public static <T, X extends RuntimeException> void isNotNull(
            T value,
            X exception
    ) {
        if (isNull(value)) {
            throw exception;
        }
    }

    public static <X extends RuntimeException> void isNotConditional(
            Boolean value,
            X exception
    ) {
        if (value) {
            throw exception;
        }
    }

    public static Boolean ensureWithinRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !isWithinRange(date, startDate, endDate);
    }

    private static Boolean isWithinRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }
}
