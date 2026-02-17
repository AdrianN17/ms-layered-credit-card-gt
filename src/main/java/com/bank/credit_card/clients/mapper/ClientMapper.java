package com.bank.credit_card.clients.mapper;

import com.bank.credit_card.clients.dto.request.ClientRequestDto;
import com.bank.credit_card.clients.dto.response.ClientResponseDto;
import com.bank.credit_card.clients.entity.ClientEntity;
import com.bank.credit_card.clients.entity.vo.ClientEntityVO;
import com.bank.credit_card.clients.schema.request.ClientRequest;
import com.bank.credit_card.clients.schema.response.ClientResponse;
import com.bank.credit_card.clients.util.EnumCardUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CardMapper.class, EnumCardUtility.class})
public interface ClientMapper {

    ClientEntity toEntity(ClientRequestDto dto);

    ClientRequestDto toDto(ClientEntity entity);

    @Mapping(source = "documentType", target = "documentType", qualifiedByName = "mapToDocumentType")
    ClientRequestDto toDto(ClientRequest clientRequest);

    ClientResponseDto toDto(ClientEntityVO entity);

    @Mapping(source = "documentType", target = "documentType", qualifiedByName = "documentTypeToString")
    ClientResponse toResponse(ClientResponseDto clientResponseDto);
}
