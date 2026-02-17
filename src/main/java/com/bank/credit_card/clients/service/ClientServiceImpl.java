package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.request.ClientRequestDto;
import com.bank.credit_card.clients.dto.response.ClientResponseDto;
import com.bank.credit_card.clients.mapper.ClientMapper;
import com.bank.credit_card.clients.repository.ClientRepository;
import com.bank.credit_card.clients.repository.ClientRepositoryVO;
import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.generic.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ClientServiceImpl extends GenericServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientRepositoryVO clientRepositoryVO;
    private final ClientMapper clientMapper;

    @Override
    public Long create(ClientRequestDto clientDto) {
        var client = clientRepository.save(clientMapper.toEntity(clientDto));
        return client.getClientId();
    }

    @Override
    @Transactional
    public Long close(Long clientId) {

        var clientEntity = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        clientEntity.softDelete();

        clientRepository.save(clientEntity);

        return clientEntity.getClientId();
    }

    @Override
    public ClientResponseDto getClient(Long clientId) {

        var clientEntity = clientRepositoryVO.findById(clientId)
                .orElseThrow(() -> new CustomBadRequest("Client not found"));

        return clientMapper.toDto(clientEntity);
    }
}
