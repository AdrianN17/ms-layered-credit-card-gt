package com.bank.credit_card.generic.service;

public interface GenericService<T, ID> {

    void save(T request);

    void delete(ID id);
}