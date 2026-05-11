package com.bank.credit_card.payment.usecase;

import com.bank.credit_card.payment.exception.PaymentPersistanceException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.bank.credit_card.generic.util.Validation.ensureWithinRange;
import static com.bank.credit_card.generic.util.Validation.isNotConditional;
import static com.bank.credit_card.payment.exception.PaymentErrorMessage.*;
import static java.util.Objects.isNull;

public class TotalPaymentUseCase extends AbstractPaymentUseCase {

    @Override
    public void close() {
        isNotConditional(isNull(paymentApprobationDate),
                new PaymentPersistanceException(PAYMENT_IS_STILL_IN_APPROBATION));
    }

    @Override
    public void validateIfPaymentIsPossible(BigDecimal available,
                                            BigDecimal total,
                                            LocalDate startDate,
                                            LocalDate endDate) {

        isNotConditional(!ensureWithinRange(paymentApprobationDate, startDate, endDate),
                new PaymentPersistanceException(DATE_NOT_WITHIN_RANGE));

        isNotConditional(total.compareTo(available.add(amount)) != 0,
                new PaymentPersistanceException(TOTAL_PAYMENT_MUST_BE_COMPLETED));
    }
}
