package com.bank.credit_card.consumption.repository;

import com.bank.credit_card.consumption.entity.ConsumptionEntityMongo;
import com.bank.credit_card.generic.repository.GenericMongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConsumptionMongoRepository extends GenericMongoRepository<ConsumptionEntityMongo, UUID> {

    @Query("{ 'cardId': ?0, 'status': 'ACTIVE', 'consumptionDate': { $gte: ?1, $lte: ?2 } }")
    List<ConsumptionEntityMongo> findByCardIdAndConsumptionDateBetween(String cardId, LocalDateTime start, LocalDateTime end);
}
