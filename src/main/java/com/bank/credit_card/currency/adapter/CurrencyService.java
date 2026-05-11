package com.bank.credit_card.currency.adapter;

import com.bank.credit_card.currency.dto.CurrencyDto;
import com.bank.credit_card.generic.enums.CurrencyEnum;

public interface CurrencyService {
    CurrencyDto get(CurrencyEnum currency);
}
