package com.bank.credit_card.generic.mapper;

import com.bank.credit_card.generic.model.Currency;

@FunctionalInterface
public interface GenericDomainCurrencyMapper<D, E, C extends Currency> {
    D toDomain(E entity, C currency);
}