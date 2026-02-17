package com.bank.credit_card.clients.service.client;

public record DocumentResponse(
        String first_name,
        String first_last_name,
        String second_last_name,
        String full_name,
        String document_number
) {
}
