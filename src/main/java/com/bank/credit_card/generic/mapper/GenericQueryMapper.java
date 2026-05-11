package com.bank.credit_card.generic.mapper;

@FunctionalInterface
public interface GenericQueryMapper<V, E> {
    V toView(E entity);
}
