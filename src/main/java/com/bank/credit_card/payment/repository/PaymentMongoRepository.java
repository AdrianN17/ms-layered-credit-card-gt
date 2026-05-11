package com.bank.credit_card.payment.repository;

import com.bank.credit_card.generic.repository.GenericMongoRepository;
import com.bank.credit_card.payment.entity.PaymentEntityMongo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentMongoRepository extends GenericMongoRepository<PaymentEntityMongo, UUID> {

    @Query("{ 'cardId': ?0, 'status': 'ACTIVE', 'paymentDate': { $gte: ?1, $lte: ?2 } }")
    List<PaymentEntityMongo> findByCardIdAndPaymentDateBetween(String cardId, LocalDateTime start, LocalDateTime end);
}


