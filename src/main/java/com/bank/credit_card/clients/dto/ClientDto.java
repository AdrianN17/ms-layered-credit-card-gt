package com.bank.credit_card.clients.dto;

import com.bank.credit_card.clients.commons.DocumentType;

import java.time.LocalDate;
import java.util.List;

public record ClientDto(
        Long clientId,
        String names,
        String documentNumber,
        DocumentType documentType,
        String lastNames,
        LocalDate birthDate,
        String phone,
        String email,
        List<CardDto> cardDtos
) {
}
