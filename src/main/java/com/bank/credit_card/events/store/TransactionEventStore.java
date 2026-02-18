package com.bank.credit_card.events.store;

import com.bank.credit_card.events.model.TransactionEvent;
import com.bank.credit_card.generic.publish.store.EventStore;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventStore implements EventStore<TransactionEvent> {

    @Override
    public void save(TransactionEvent event) {
        // guardar en colección "purchase_events"
    }
}
