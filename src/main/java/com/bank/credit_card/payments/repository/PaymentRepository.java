package com.bank.credit_card.payments.repository;

import com.bank.credit_card.generic.repository.GenericRepository;
import com.bank.credit_card.payments.entity.PaymentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends GenericRepository<PaymentEntity, Long> {
}
