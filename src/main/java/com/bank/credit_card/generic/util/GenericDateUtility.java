package com.bank.credit_card.generic.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GenericDateUtility {
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    public static LocalDateTime getStartDay(LocalDate date) {
        return date.atStartOfDay();
    }

    public static LocalDateTime getStartDay(Short dayOfMonth) {
       return  getCurrentLocalDate()
                .withDayOfMonth(dayOfMonth)
                .atStartOfDay();
    }

    public static LocalDateTime getEndDay(LocalDateTime date) {
        return date
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999_999_999);
    }

    public static LocalDateTime getEndDay(LocalDate date) {
        return date.atStartOfDay()
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999_999_999);
    }
}
