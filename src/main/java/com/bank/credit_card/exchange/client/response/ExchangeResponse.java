package com.bank.credit_card.exchange.client.response;

public record ExchangeResponse(String base,
                               ResultResponse result,
                               String updated,
                               Integer ms) {
}
