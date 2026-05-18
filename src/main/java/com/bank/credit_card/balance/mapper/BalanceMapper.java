package com.bank.credit_card.balance.mapper;

import com.bank.credit_card.balance.dto.BalanceDtoRequest;
import com.bank.credit_card.balance.entity.BalanceEntity;
import com.bank.credit_card.card.schema.request.CardRequestAccount;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.generic.mapper.EntityMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.bank.credit_card.balance.constant.DateRangeConstant.NEXT_MONTH;

@Component
public class BalanceMapper implements EntityMapper<BalanceDtoRequest, BalanceEntity> {

    public BalanceDtoRequest toDto(CardRequestAccount request, Long cardId)
    {
        return new BalanceDtoRequest(
                CurrencyEnum.ofCode(request.getCurrency()),
                cardId,
                request.getCreditTotal(),
                Short.valueOf(request.getPaymentDate())
        );
    }

    public BalanceEntity toEntity(BalanceDtoRequest request) {

        LocalDate startDate = LocalDate.now().withDayOfMonth(request.paymentDay());
        LocalDate endDate = startDate.plusMonths(NEXT_MONTH);

        return BalanceEntity.builder()
                .cardId(request.cardId())
                .totalAmount(request.total())
                .availableAmount(request.total())
                .oldAmount(request.total())
                .currency(request.currency())
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}

