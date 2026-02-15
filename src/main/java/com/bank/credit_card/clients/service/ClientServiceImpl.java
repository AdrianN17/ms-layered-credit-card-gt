package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.CardDto;
import com.bank.credit_card.clients.dto.ClientDto;
import com.bank.credit_card.clients.mapper.CardMapper;
import com.bank.credit_card.clients.mapper.ClientMapper;
import com.bank.credit_card.clients.repository.ClientRepository;
import com.bank.credit_card.exceptions.CustomBadRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final CardMapper cardMapper;

    @Override
    public void createClient(ClientDto clientDto) {
        clientRepository.save(clientMapper.toEntity(clientDto));
    }

    @Override
    @Transactional
    public void closeClient(Long clientId) {

        var clientEntity = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Optional.ofNullable(clientEntity.getCardEntities())
                .stream()
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .forEach(cardEntity -> {

                    cardEntity.softDelete();
                    if (!isNull(cardEntity.getCardAccount())) {
                        cardEntity.getCardAccount().softDelete();
                    }
                });

        clientRepository.save(clientEntity);
    }

    @Override
    public ClientDto getClientById(Long clientId) {

        var clientEntity = clientRepository.findById(clientId)
                .orElseThrow(() -> new CustomBadRequest("Client not found"));

        return clientMapper.toDto(clientEntity);
    }

    @Override
    public void registerCardToClient(CardDto cardDto, Long clientId) {

        var clientEntity = clientRepository.findById(clientId)
                .orElseThrow(() -> new CustomBadRequest("Client not found"));

        clientEntity.getCardEntities().add(cardMapper.toEntity(cardDto));
        clientRepository.save(clientEntity);

    }
}
