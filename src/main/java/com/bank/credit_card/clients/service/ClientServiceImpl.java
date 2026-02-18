package com.bank.credit_card.clients.service;

import com.bank.credit_card.clients.dto.request.ClientRequestDto;
import com.bank.credit_card.clients.dto.response.ClientResponseDto;
import com.bank.credit_card.clients.mapper.ClientMapper;
import com.bank.credit_card.clients.repository.ClientRepository;
import com.bank.credit_card.clients.repository.ClientRepositoryVO;
import com.bank.credit_card.generic.publish.publisher.GenericEventPublisher;
import com.bank.credit_card.generic.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bank.credit_card.clients.constant.ClientConstant.CLIENT_NOT_FOUND;
import static com.bank.credit_card.clients.util.PublishClientUtility.closeEvent;
import static com.bank.credit_card.clients.util.PublishClientUtility.createEvent;
import static com.bank.credit_card.generic.util.GenericErrorsUtility.thrownNotFound;

@Service
@AllArgsConstructor
public class ClientServiceImpl extends GenericServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientRepositoryVO clientRepositoryVO;
    private final ClientMapper clientMapper;
    private final GenericEventPublisher genericEventPublisher;

    @Override
    public Long create(ClientRequestDto clientDto) {
        var client = clientRepository.save(clientMapper.toEntity(clientDto));
        createEvent(genericEventPublisher, client.getClientId());
        return client.getClientId();
    }

    @Override
    @Transactional
    public Long close(Long clientId) {

        var clientEntity = clientRepository.findById(clientId)
                .orElseThrow(() -> thrownNotFound(CLIENT_NOT_FOUND));

        clientEntity.softDelete();
        clientRepository.save(clientEntity);
        closeEvent(genericEventPublisher, clientId);
        return clientEntity.getClientId();
    }

    @Override
    public ClientResponseDto getClient(Long clientId) {

        var clientEntity = clientRepositoryVO.findById(clientId)
                .orElseThrow(() -> thrownNotFound(CLIENT_NOT_FOUND));

        return clientMapper.toDto(clientEntity);
    }
}
