package com.bank.credit_card.currency.service;

import com.bank.credit_card.currency.repository.CurrencyJsonServerWSRepository;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.generic.exception.BadGatewayException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.bank.credit_card.currency.exception.CurrencyErrorMessage.CURRENCY_NOT_FOUND;


@Service
@AllArgsConstructor
public class CurrencyServiceaImpl implements CurrencyService {

    private final CurrencyJsonServerWSRepository currencyJsonServerWSRepository;

    @Override
    public BigDecimal get(CurrencyEnum currencyCard,
                           CurrencyEnum amountCurrency,
                           BigDecimal amount

    ) {

        if(currencyCard.equals(amountCurrency)) {
            return amount;
        }
        else
        {
            var currency = currencyJsonServerWSRepository.findByCurrency(currencyCard)
                    .orElseThrow(() ->
                            new BadGatewayException(
                                    String.format(CURRENCY_NOT_FOUND, currencyCard)));

            return currency.value().multiply(amount);
        }



    }
}
