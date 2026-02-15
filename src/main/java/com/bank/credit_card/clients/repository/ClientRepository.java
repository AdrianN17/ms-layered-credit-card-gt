package com.bank.credit_card.clients.repository;

import com.bank.credit_card.clients.entity.ClientEntity;
import com.bank.credit_card.generic.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends GenericRepository<ClientEntity, Long> {
}
