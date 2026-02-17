package com.bank.credit_card.clients.dto.response;

import com.bank.credit_card.clients.commons.DocumentType;

import java.time.LocalDate;
import java.util.List;

public record ClientResponseDto(
        Long clientId,
        String lastNames,
        String names,
        LocalDate birthDate,
        String documentNumber,
        DocumentType documentType,
        String phone,
        String email,
        List<CardResponseDto> cardResponseDtoList
) {
}
