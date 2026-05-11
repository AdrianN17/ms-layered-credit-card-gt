package com.bank.credit_card.payment.usecase;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PaymentUseCase {
    void close();

    void validateIfPaymentIsPossible(BigDecimal available,
                                     BigDecimal total,
                                     LocalDate startDate,
                                     LocalDate endDate);
}
