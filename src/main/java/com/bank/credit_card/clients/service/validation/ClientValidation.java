package com.bank.credit_card.clients.service.validation;

import com.bank.credit_card.clients.dto.request.ClientRequestDto;
import com.bank.credit_card.clients.repository.ClientRepository;
import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.exchange.FeignDocument;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static com.bank.credit_card.clients.commons.DocumentType.DNI;

@Component
@AllArgsConstructor
public class ClientValidation {

    private final ClientRepository clientRepository;
    private final FeignDocument feignDocument;

    public void validateEmail(ClientRequestDto clientRequestDto) {
        Optional.of(clientRepository
                        .existEmail(clientRequestDto.email()))
                .ifPresent(b -> {
                    throw new CustomBadRequest("Email already exists");
                });
    }

    public void validatePhone(ClientRequestDto clientRequestDto) {
        Optional.of(clientRepository
                        .existPhone(clientRequestDto.phone()))
                .ifPresent(b -> {
                    throw new CustomBadRequest("Phone already exists");
                });
    }

    public void validateDocuments(ClientRequestDto clientRequestDto) {
        Optional.of(clientRepository
                        .existDocuments(clientRequestDto.documentNumber(), clientRequestDto.documentType().getValue()))
                .ifPresent(b -> {
                    throw new CustomBadRequest("Document already exists");
                });
    }

    public void validateNames(ClientRequestDto clientRequestDto) {

        if (Objects.equals(clientRequestDto.documentType(), DNI)) {
            var documentsResponse = feignDocument.getDocument(clientRequestDto.documentNumber());

            if (documentsResponse.isPresent()) {
                var name = documentsResponse.get().full_name().toLowerCase();
                var val1 = name.contains(clientRequestDto.names().toLowerCase());
                var val2 = name.contains(clientRequestDto.lastNames().toLowerCase());

                if (!val1 || !val2) {
                    throw new CustomBadRequest("Names do not match with document information");
                }
            }

        }


    }
}
