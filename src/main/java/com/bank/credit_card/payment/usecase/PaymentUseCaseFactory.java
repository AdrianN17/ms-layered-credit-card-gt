package com.bank.credit_card.payment.usecase;

import com.bank.credit_card.payment.enums.CategoryPaymentEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentUseCaseFactory {

    public static PaymentUseCase create(BigDecimal amount,
                                        LocalDate paymentApprobationDate,
                                        CategoryPaymentEnum category) {
        return switch (category) {
            case NORMAL     -> build(new NormalPaymentUseCase(), amount, paymentApprobationDate);
            case TOTAL      -> build(new TotalPaymentUseCase(), amount, paymentApprobationDate);
            case MINIMO     -> build(new MinimumPaymentUseCase(), amount, paymentApprobationDate);
            case ADELANTADO -> build(new PrepaymentUseCase(), amount, paymentApprobationDate);
        };
    }

    private static PaymentUseCase build(AbstractPaymentUseCase useCase,
                                        BigDecimal amount,
                                        LocalDate paymentApprobationDate) {
        useCase.amount                 = amount;
        useCase.paymentApprobationDate = paymentApprobationDate;
        return useCase;
    }
}
