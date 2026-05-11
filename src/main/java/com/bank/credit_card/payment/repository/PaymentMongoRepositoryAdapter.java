package com.bank.credit_card.payment.repository;

import com.bank.credit_card.generic.enums.StatusEnum;
import com.bank.credit_card.payment.entity.PaymentEntity;
import com.bank.credit_card.payment.entity.PaymentEntityMongo;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Profile("old")
@AllArgsConstructor
public class PaymentMongoRepositoryAdapter implements PaymentRepository {

    private final PaymentMongoRepository repository;

    @Override
    public PaymentEntity save(PaymentEntity entity) {
        return repository.save((PaymentEntityMongo) entity);
    }

    @Override
    public Optional<PaymentEntity> findById(UUID id) {
        return repository.findById(id).map(e -> (PaymentEntity) e);
    }

    @Override
    public List<PaymentEntity> findByCardIdAndPaymentDateBetween(String cardId, LocalDateTime start, LocalDateTime end) {
        return repository.findByCardIdAndPaymentDateBetween(cardId, start, end)
                .stream()
                .map(e -> (PaymentEntity) e)
                .toList();
    }

    @Override
    public void softDelete(UUID id) {
        repository.findById(id).ifPresent(entity -> {
            entity.setStatus(StatusEnum.INACTIVE);
            repository.save(entity);
        });
    }
}
