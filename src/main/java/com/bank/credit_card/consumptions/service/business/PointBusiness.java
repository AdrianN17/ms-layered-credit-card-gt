package com.bank.credit_card.consumptions.service.business;

import com.bank.credit_card.clients.commons.CategoryCard;

import java.math.BigDecimal;

@FunctionalInterface
public interface PointBusiness {
    Integer calculatePoints(BigDecimal amount, CategoryCard categoryCard);
}