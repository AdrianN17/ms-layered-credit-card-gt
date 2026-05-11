package com.bank.credit_card.balance.usecase;

import com.bank.credit_card.balance.entity.BalanceEntity;
import com.bank.credit_card.balance.enums.BalanceUseCaseEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BalanceUseCaseFactory {

    private final ConsumptionBalanceUseCase consumptionUseCase;
    private final PaymentBalanceUseCase paymentUseCase;

    public BalanceUseCase create(BalanceUseCaseEnum type, BalanceEntity entity) {
        return switch (type) {
            case CONSUMPTION -> consumptionUseCase.withEntity(entity);
            case PAYMENT     -> paymentUseCase.withEntity(entity);
        };
    }
}
