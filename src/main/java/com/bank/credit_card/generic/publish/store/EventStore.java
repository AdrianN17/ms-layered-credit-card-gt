package com.bank.credit_card.generic.publish.store;

import com.bank.credit_card.generic.publish.model.GenericEvent;

public interface EventStore<T extends GenericEvent> {
    void save(T event);
}
