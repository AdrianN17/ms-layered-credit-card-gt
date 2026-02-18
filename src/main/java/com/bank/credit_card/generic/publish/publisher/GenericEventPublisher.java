package com.bank.credit_card.generic.publish.publisher;


import com.bank.credit_card.generic.publish.model.GenericEvent;

public interface GenericEventPublisher {
    void publish(GenericEvent event);
}
