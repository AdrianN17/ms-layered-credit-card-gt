package com.bank.credit_card.payment.usecase;

import com.bank.credit_card.generic.exception.UnprocessableEntityException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static com.bank.credit_card.generic.util.Validation.ensureWithinRange;
import static com.bank.credit_card.generic.util.Validation.isNotConditional;
import static com.bank.credit_card.payment.exception.PaymentErrorMessage.*;
import static java.util.Objects.isNull;

public class MinimumPaymentUseCase extends AbstractPaymentUseCase {

    @Override
    public void close() {
        isNotConditional(isNull(paymentApprobationDate),
                new UnprocessableEntityException(PAYMENT_IS_STILL_IN_APPROBATION));
    }

    @Override
    public void validateIfPaymentIsPossible(BigDecimal available, BigDecimal total,
                                            LocalDate startDate, LocalDate endDate) {
        isNotConditional(!ensureWithinRange(paymentApprobationDate, startDate, endDate),
                new UnprocessableEntityException(DATE_NOT_WITHIN_RANGE));

        isNotConditional(total.compareTo(available) == 0,
                new UnprocessableEntityException(PAYMENT_IT_NOT_NECCESARY));

        BigDecimal totalAvailableFraction = total.subtract(available)
                .divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);

        isNotConditional(totalAvailableFraction.compareTo(amount) < 0,
                new UnprocessableEntityException(MINIMUN_PAYMENT_MUST_BE_THIRD_PART_OF_AVAILABLE));
    }
}
