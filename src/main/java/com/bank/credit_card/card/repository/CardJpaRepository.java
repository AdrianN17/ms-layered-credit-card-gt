package com.bank.credit_card.card.repository;

import com.bank.credit_card.card.entity.CardEntity;
import com.bank.credit_card.generic.repository.GenericJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardJpaRepository extends GenericJpaRepository<CardEntity, Long> {
}
