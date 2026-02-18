package com.bank.credit_card.events.store;

import com.bank.credit_card.events.model.BonusPoints;
import com.bank.credit_card.generic.publish.store.EventStore;
import org.springframework.stereotype.Component;

@Component
public class BonusEventStore implements EventStore<BonusPoints> {

    @Override
    public void save(BonusPoints event) {
        // guardar en colección "bonus_events"
    }
}
