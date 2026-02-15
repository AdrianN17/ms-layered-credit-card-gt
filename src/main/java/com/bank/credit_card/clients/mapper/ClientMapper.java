package com.bank.credit_card.clients.mapper;

import com.bank.credit_card.clients.dto.CardDto;
import com.bank.credit_card.clients.dto.ClientDto;
import com.bank.credit_card.clients.entity.ClientEntity;
import com.bank.credit_card.clients.schema.request.ClientRequest;
import com.bank.credit_card.clients.schema.response.ClientResponse;
import com.bank.credit_card.clients.util.EnumCardUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CardMapper.class, EnumCardUtility.class})
public interface ClientMapper {

    @Mapping(source = "cardDtos", target = "cardEntities")
    ClientEntity toEntity(ClientDto dto);

    @Mapping(source = "cardEntities", target = "cardDtos")
    ClientDto toDto(ClientEntity entity);

    @Mapping(target = "clientId", ignore = true)
    @Mapping(source = "clientRequest.names", target = "names")
    @Mapping(source = "clientRequest.lastNames", target = "lastNames")
    @Mapping(source = "clientRequest.birthDate", target = "birthDate")
    @Mapping(source = "clientRequest.phone", target = "phone")
    @Mapping(source = "clientRequest.email", target = "email")
    @Mapping(source = "clientRequest.documentNumber", target = "documentNumber")
    @Mapping(source = "clientRequest.documentType", target = "documentType", qualifiedByName = "mapToDocumentType")
    @Mapping(source = "cardDtos", target = "cardDtos")
    ClientDto toDto(ClientRequest clientRequest, List<CardDto> cardDtos);


    @Mapping(source = "clientId", target = "clientId")
    @Mapping(source = "names", target = "names")
    @Mapping(source = "lastNames", target = "lastNames")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "documentNumber", target = "documentNumber")
    @Mapping(source = "documentType", target = "documentType", qualifiedByName = "documentTypeToString")
    ClientResponse toResponse(ClientDto clientDto);
}
