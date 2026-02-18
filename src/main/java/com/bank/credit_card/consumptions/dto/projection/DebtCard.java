package com.bank.credit_card.consumptions.dto.projection;

import java.math.BigDecimal;

public interface DebtCard {
    Long getCurrency();
    BigDecimal getAmount();

}
