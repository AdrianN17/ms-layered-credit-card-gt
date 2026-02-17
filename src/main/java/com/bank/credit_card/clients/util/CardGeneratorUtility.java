package com.bank.credit_card.clients.util;

import java.time.LocalDate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardGeneratorUtility {
    public static String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = (int) (Math.random() * 10);
            cardNumber.append(digit);
        }
        return cardNumber.toString();
    }

    public static String generateCVV() {
        StringBuilder cvv = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int digit = (int) (Math.random() * 10);
            cvv.append(digit);
        }
        return cvv.toString();
    }

    public static String generateExpirationDate() {
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();
        return String.format("%02d/%d", month, year);
    }
}
