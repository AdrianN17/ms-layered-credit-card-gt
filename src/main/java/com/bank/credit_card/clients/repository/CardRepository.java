package com.bank.credit_card.clients.repository;

import com.bank.credit_card.clients.entity.CardEntity;
import com.bank.credit_card.generic.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends GenericRepository<CardEntity, Long> {
}
