package com.bank.credit_card.clients.repository;

import com.bank.credit_card.clients.entity.ClientEntity;
import com.bank.credit_card.generic.repository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends GenericRepository<ClientEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ClientEntity c WHERE c.email = :email")
    Boolean existEmail(String email);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ClientEntity c WHERE c.phone = :phone")
    Boolean existPhone(String phone);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ClientEntity c " +
            "WHERE c.documentNumber = :documentNumber AND c.documentType = :documentType")
    Boolean existDocuments(String documentNumber, Integer documentType);
}
