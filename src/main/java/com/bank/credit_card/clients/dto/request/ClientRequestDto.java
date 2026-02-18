package com.bank.credit_card.clients.dto.request;

import com.bank.credit_card.clients.commons.DocumentType;

import java.time.LocalDate;

public record ClientRequestDto(
        String names,
        String documentNumber,
        DocumentType documentType,
        String lastNames,
        LocalDate birthDate,
        String phone,
        String email
) {
}
