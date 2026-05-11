package com.bank.credit_card.generic.mapper;

@FunctionalInterface
public interface GenericEntityMapper<D, E> {
    E toEntity(D domain);
}
