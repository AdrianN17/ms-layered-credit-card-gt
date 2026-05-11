package com.bank.credit_card.generic.mapper;

@FunctionalInterface
public interface GenericDomainMapper<D, E> {
    D toDomain(E entity);
}
