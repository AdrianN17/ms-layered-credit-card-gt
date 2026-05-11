package com.bank.credit_card.payment.usecase;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class AbstractPaymentUseCase implements PaymentUseCase {

    BigDecimal amount;
    LocalDate paymentApprobationDate;
}


