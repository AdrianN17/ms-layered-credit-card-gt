package com.bank.credit_card.balance.mapper;

import com.bank.credit_card.balance.dto.BalanceDtoRequest;
import com.bank.credit_card.balance.entity.BalanceEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.bank.credit_card.balance.constants.DateRangeConstant.NEXT_MONTH;

@Component
public class BalanceMapper {

    public BalanceEntity toEntity(BalanceDtoRequest request, Long cardId) {


        LocalDate startDate = LocalDate.now().withDayOfMonth(request.paymentDay());
        LocalDate endDate = startDate.plusMonths(NEXT_MONTH);

        return BalanceEntity.builder()
                .cardId(cardId)
                .totalAmount(request.total())
                .availableAmount(request.total())
                .oldAmount(request.total())
                .currency(request.currency())
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}

