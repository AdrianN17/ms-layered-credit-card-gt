package com.bank.credit_card.consumptions.policy;

import com.bank.credit_card.clients.commons.CategoryCard;

import java.math.BigDecimal;

public interface PointsPolicy {
    int calculatePoints(BigDecimal amount, CategoryCard categoryCard);
}