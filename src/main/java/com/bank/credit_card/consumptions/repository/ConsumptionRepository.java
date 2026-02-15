package com.bank.credit_card.consumptions.repository;

import com.bank.credit_card.consumptions.entity.ConsumptionEntity;
import com.bank.credit_card.generic.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionRepository extends GenericRepository<ConsumptionEntity, Long> {
}
