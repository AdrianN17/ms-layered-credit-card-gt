package com.bank.credit_card.generic.service;

public interface GenericService<D,ID> {

    ID create(D d);

    ID close(ID id);
}
