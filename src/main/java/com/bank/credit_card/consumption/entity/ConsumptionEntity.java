package com.bank.credit_card.consumption.entity;

import com.bank.credit_card.generic.enums.CurrencyEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface ConsumptionEntity {

    UUID getConsumptionId();

    String getCardId();

    String getSellerName();

    CurrencyEnum getCurrency();

    BigDecimal getAmount();

    LocalDateTime getConsumptionDate();

    LocalDateTime getConsumptionApprobationDate();
}

