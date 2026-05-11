package com.bank.credit_card.currency.adapter;

import com.bank.credit_card.currency.dto.CurrencyDto;
import com.bank.credit_card.currency.exception.ConverterWSClientException;
import com.bank.credit_card.currency.repository.CurrencyJsonServerWSRepository;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bank.credit_card.currency.constant.CurrencyErrorMessage.CURRENCY_NOT_FOUND;


@Service
@AllArgsConstructor
public class CurrencyServiceaImpl implements CurrencyService {

    private final CurrencyJsonServerWSRepository currencyJsonServerWSRepository;

    @Override
    public CurrencyDto get(CurrencyEnum currency) {
        return currencyJsonServerWSRepository.findByCurrency(currency)
                .orElseThrow(() ->
                        new ConverterWSClientException(
                                String.format(CURRENCY_NOT_FOUND, currency)));
    }
}
