package com.bank.credit_card.clients.repository;

import com.bank.credit_card.clients.entity.CardAccountEntity;
import com.bank.credit_card.generic.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardAccountRepository extends GenericRepository<CardAccountEntity, Long> {

}
