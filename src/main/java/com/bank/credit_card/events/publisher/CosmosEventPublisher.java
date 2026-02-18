package com.bank.credit_card.events.publisher;

import com.bank.credit_card.events.model.BonusPoints;
import com.bank.credit_card.events.model.TransactionEvent;
import com.bank.credit_card.events.store.BonusEventStore;
import com.bank.credit_card.events.store.TransactionEventStore;
import com.bank.credit_card.generic.publish.model.GenericEvent;
import com.bank.credit_card.generic.publish.publisher.GenericEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CosmosEventPublisher implements GenericEventPublisher {

    private final TransactionEventStore transactionEventStore;
    private final BonusEventStore bonusEventStore;

    @Override
    public void publish(GenericEvent event) {

        if (event instanceof BonusPoints purchaseEvent) {
            bonusEventStore.save(purchaseEvent);
        }

        if (event instanceof TransactionEvent transactionEvent) {
            transactionEventStore.save(transactionEvent);
        }
    }
}
