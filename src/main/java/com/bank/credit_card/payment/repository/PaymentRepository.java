package com.bank.credit_card.payment.repository;

import com.bank.credit_card.payment.entity.PaymentEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    PaymentEntity save(PaymentEntity entity);

    Optional<PaymentEntity> findById(UUID id);

    List<PaymentEntity> findByCardIdAndPaymentDateBetween(String cardId, LocalDate start, LocalDate end);

    void softDelete(UUID id);
}
