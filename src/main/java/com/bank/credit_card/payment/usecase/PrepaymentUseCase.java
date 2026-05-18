package com.bank.credit_card.payment.usecase;

import com.bank.credit_card.generic.exception.UnprocessableEntityException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.bank.credit_card.generic.util.Validation.ensureWithinRange;
import static com.bank.credit_card.generic.util.Validation.isNotConditional;
import static com.bank.credit_card.payment.exception.PaymentErrorMessage.*;
import static java.util.Objects.isNull;

public class PrepaymentUseCase extends AbstractPaymentUseCase {

    @Override
    public void close() {
        isNotConditional(isNull(paymentApprobationDate),
                new UnprocessableEntityException(PAYMENT_IS_STILL_IN_APPROBATION));
    }

    @Override
    public void validateIfPaymentIsPossible(BigDecimal available, BigDecimal total,
                                            LocalDate startDate, LocalDate endDate) {
        isNotConditional(ensureWithinRange(paymentApprobationDate, startDate, endDate),
                new UnprocessableEntityException(DATE_NOT_WITHIN_RANGE));

        isNotConditional(total.compareTo(amount) == 0,
                new UnprocessableEntityException(PAYMENT_IT_NOT_NECCESARY));
    }
}
