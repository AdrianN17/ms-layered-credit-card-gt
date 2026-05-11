package com.bank.credit_card.generic.model;

import com.bank.credit_card.generic.enums.CurrencyEnum;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static com.bank.credit_card.generic.exception.AmountErrorMessage.AMOUNT_NEGATIVE;
import static com.bank.credit_card.generic.exception.AmountErrorMessage.AMOUNT_REQUIRED;
import static com.bank.credit_card.generic.util.Validation.isNotNull;

@Getter
public final class Amount {

    private final CurrencyEnum currency;
    private final BigDecimal exchangeRate;
    private final BigDecimal amount;

    private Amount(CurrencyEnum currency, BigDecimal exchangeRate, BigDecimal amount) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
        this.amount = amount;
    }

    public static Amount create(CurrencyEnum currency, BigDecimal exchangeRate, BigDecimal amount) {
        isNotNull(amount, new RuntimeException(AMOUNT_REQUIRED));
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException(AMOUNT_NEGATIVE);
        return new Amount(currency, exchangeRate, amount);
    }

    public static Amount create(CurrencyEnum currency, BigDecimal amount) {
        return create(currency, BigDecimal.ONE, amount);
    }

    public Boolean estaVacio() {
        return this.amount.compareTo(BigDecimal.ZERO) == 0;
    }

    public Boolean estaFaltando(Amount amount) {
        return getAmount().compareTo(amount.getAmount()) > 0;
    }

    public Amount mas(Amount amount) {
        return Amount.create(getCurrency(), getExchangeRate(),
                getAmount().add(convertir(this, amount).getAmount()));
    }

    public Amount menos(Amount amount) {
        return Amount.create(getCurrency(), getExchangeRate(),
                this.amount.subtract(convertir(this, amount).getAmount()));
    }

    public Amount dividir(Integer quantity) {
        return Amount.create(getCurrency(), getExchangeRate(),
                this.amount.divide(BigDecimal.valueOf(quantity), 2, RoundingMode.HALF_UP));
    }

    public static Amount convertir(Amount a, Amount b) {
        if (!Objects.equals(a.getCurrency(), b.getCurrency())) {
            BigDecimal converted = b.getAmount().multiply(b.getExchangeRate() != null ? b.getExchangeRate() : BigDecimal.ONE);
            return Amount.create(a.getCurrency(), a.getExchangeRate(), converted);
        }
        return b;
    }

    public Boolean estaSobrando(Amount amount) {
        return this.getAmount().compareTo(amount.getAmount()) < 0;
    }

    public Boolean esIgual(Amount amount) {
        return this.getAmount().compareTo(amount.getAmount()) == 0;
    }

    public Amount deuda(BigDecimal debtTax) {
        return Amount.create(getCurrency(), getExchangeRate(),
                this.amount.add(debtTax));
    }

    public Amount fraccionar(Integer quantity, BigDecimal debtTax) {
        return Amount.create(getCurrency(), getExchangeRate(),
                amount.divide(BigDecimal.valueOf(quantity), 2, RoundingMode.HALF_UP)
                        .add(amount.multiply(debtTax)));
    }

    public Amount descuento(BigDecimal discount) {
        return Amount.create(getCurrency(), getExchangeRate(),
                this.amount.subtract(discount));
    }

    public Boolean sonIguales(Amount other) {
        return Objects.equals(getCurrency(), other.getCurrency());
    }

    @Override
    public String toString() {
        return amount.toString() + " " + currency.getCode() + exchangeRate.toString();
    }
}