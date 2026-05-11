package com.bank.credit_card.balance.usecase;

import com.bank.credit_card.balance.entity.BalanceEntity;
import com.bank.credit_card.balance.exception.BalancePersistanceException;
import com.bank.credit_card.balance.repository.BalanceJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.bank.credit_card.balance.exception.BalanceErrorMessage.PAYMENT_CANNOT_BE_NULL;
import static com.bank.credit_card.balance.exception.BalanceErrorMessage.PAYMENT_CATEGORY_EXCEED_LIKE;
import static com.bank.credit_card.generic.util.Validation.isNotConditional;
import static com.bank.credit_card.generic.util.Validation.isNotNull;

@Component
@AllArgsConstructor
public class PaymentBalanceUseCase implements BalanceUseCase {

    private final BalanceJpaRepository balanceJpaRepository;
    private BalanceEntity entity;

    @Override
    public void apply(BigDecimal amount) {
        isNotNull(amount, new BalancePersistanceException(PAYMENT_CANNOT_BE_NULL));

        BigDecimal available    = entity.getAvailableAmount();
        BigDecimal total        = entity.getTotalAmount();
        BigDecimal newAvailable = available.add(amount);

        isNotConditional(newAvailable.compareTo(total) > 0,
                new BalancePersistanceException(PAYMENT_CATEGORY_EXCEED_LIKE
                        + newAvailable.subtract(total)));

        entity.setAvailableAmount(newAvailable);
        balanceJpaRepository.save(entity);
    }

    @Override
    public void cancel(BigDecimal amount) {
        isNotNull(amount, new BalancePersistanceException(PAYMENT_CANNOT_BE_NULL));

        BigDecimal available = entity.getAvailableAmount();

        entity.setAvailableAmount(available.subtract(amount));
        balanceJpaRepository.save(entity);
    }

    public PaymentBalanceUseCase withEntity(BalanceEntity entity) {
        this.entity = entity;
        return this;
    }
}
