package com.bank.credit_card.clients.service.client;

import com.bank.credit_card.clients.config.FeignDocumentConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(value = "documents", url = "${documents.url}", configuration = FeignDocumentConfig.class)
public interface FeignDocument {

    @RequestMapping(method = RequestMethod.GET, value = "${documents.endpoint.dni}")
    Optional<DocumentResponse> getDocument(
            @PathVariable("documentId") Long documentId
    );
}
