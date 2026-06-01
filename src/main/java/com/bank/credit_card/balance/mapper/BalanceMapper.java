package com.bank.credit_card.balance.mapper;

import com.bank.credit_card.balance.dto.BalanceDtoRequest;
import com.bank.credit_card.balance.entity.BalanceEntity;
import com.bank.credit_card.card.schema.request.CardRequestAccount;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.generic.mapper.EntityMapper;
import com.bank.credit_card.generic.mapper.RequestIDDtoMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.bank.credit_card.balance.constant.DateRangeConstant.NEXT_MONTH;

@Component
public class BalanceMapper implements
        RequestIDDtoMapper<CardRequestAccount, BalanceDtoRequest>,
        EntityMapper<BalanceDtoRequest, BalanceEntity> {

    @Override
    public BalanceDtoRequest toRequestDto(CardRequestAccount request, Long cardId, Long id)
    {
        return new BalanceDtoRequest(
                id,
                CurrencyEnum.ofCode(request.getCurrency()),
                cardId,
                request.getCreditTotal(),
                Short.valueOf(request.getPaymentDate())
        );
    }

    @Override
    public BalanceEntity toEntity(BalanceDtoRequest request) {

        LocalDate today = LocalDate.now();

        LocalDate startDate = today.withDayOfMonth(request.paymentDay());

        if (today.isBefore(startDate)) {
            startDate = startDate.minusMonths(1);
        }

        LocalDate endDate = startDate.plusMonths(1);

        return BalanceEntity.builder()
                .idBalance(request.idBalance())
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

