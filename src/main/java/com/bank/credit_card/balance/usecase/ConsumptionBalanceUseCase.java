package com.bank.credit_card.balance.usecase;

import com.bank.credit_card.balance.entity.BalanceEntity;
import com.bank.credit_card.balance.repository.BalanceJpaRepository;
import com.bank.credit_card.generic.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.bank.credit_card.balance.exception.BalanceErrorMessage.AMOUNT_EXCEED_CREDIT_LIMIT;
import static com.bank.credit_card.generic.util.Validation.isNotConditional;

@Component
@RequiredArgsConstructor
public class ConsumptionBalanceUseCase implements BalanceUseCase {

    private final BalanceJpaRepository balanceJpaRepository;
    private BalanceEntity entity;

    @Override
    public void apply(BigDecimal amount) {

        BigDecimal available    = entity.getAvailableAmount();
        BigDecimal total        = entity.getTotalAmount();
        BigDecimal newAvailable = available.subtract(amount);

        isNotConditional(newAvailable.compareTo(total) > 0,
                new UnprocessableEntityException(AMOUNT_EXCEED_CREDIT_LIMIT));

        entity.setAvailableAmount(newAvailable);
        balanceJpaRepository.save(entity);
    }

    @Override
    public void cancel(BigDecimal amount) {

        BigDecimal available = entity.getAvailableAmount();

        entity.setAvailableAmount(available.add(amount));
        balanceJpaRepository.save(entity);
    }

    public ConsumptionBalanceUseCase withEntity(BalanceEntity entity) {
        this.entity = entity;
        return this;
    }
}
