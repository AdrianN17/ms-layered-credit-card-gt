package com.bank.credit_card.consumptions.service.business;

import com.bank.credit_card.clients.commons.CategoryCard;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PointBusinessImpl implements PointBusiness {

    @Override
    public Integer calculatePoints(BigDecimal amount, CategoryCard categoryCard) {
        BigDecimal ratio = switch (categoryCard) {
            case NORMAL -> BigDecimal.valueOf(10);
            case SILVER -> BigDecimal.valueOf(8);
            case GOLD -> BigDecimal.valueOf(6);
            case PLATINUM -> BigDecimal.valueOf(4);
            case BLACK -> BigDecimal.valueOf(3);
            case SIGNATURE -> BigDecimal.valueOf(2);
            case INFINITY -> BigDecimal.valueOf(1);
        };

        return amount.divide(ratio, RoundingMode.DOWN).intValue();
    }
}
