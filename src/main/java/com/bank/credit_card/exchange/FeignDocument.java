package com.bank.credit_card.exchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(value = "documents", url = "https://api.decolecta.com/v1/reniec/")
public interface FeignDocument {

    @RequestMapping(method = RequestMethod.GET, value = "/dni?numero=documentId")
    Optional<DocumentResponse> getDocument(
            @PathVariable("documentId") Long documentId
    );
}
