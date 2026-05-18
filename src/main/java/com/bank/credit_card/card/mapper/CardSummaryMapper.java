package com.bank.credit_card.card.mapper;

import com.bank.credit_card.card.dto.BalanceDtoResponse;
import com.bank.credit_card.card.dto.BenefitDtoResponse;
import com.bank.credit_card.card.dto.CardAccountDtoResponse;
import com.bank.credit_card.card.dto.CardDtoResponse;
import com.bank.credit_card.card.entity.projection.CardSumaryProjection;
import com.bank.credit_card.card.schema.response.CardResponse;
import com.bank.credit_card.card.schema.response.CardResponseAccount;
import com.bank.credit_card.card.schema.response.CardResponseBalance;
import com.bank.credit_card.card.schema.response.CardResponseBenefit;
import com.bank.credit_card.generic.mapper.RequestMapper;
import com.bank.credit_card.generic.mapper.ResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class CardSummaryMapper implements RequestMapper<CardSumaryProjection, CardDtoResponse>,
                                          ResponseMapper<CardDtoResponse, CardResponse> {

    private CardAccountDtoResponse toAccountDto(CardSumaryProjection projection) {
        return new CardAccountDtoResponse(
                projection.getCreditTotal(),
                projection.getDebtTax(),
                projection.getCurrencyEnum(),
                projection.getPaymentDate() != null ? projection.getPaymentDate().shortValue() : null,
                projection.getCardStatusEnum()
        );
    }

    private BalanceDtoResponse toBalanceDto(CardSumaryProjection projection) {
        return new BalanceDtoResponse(
                projection.getTotalAmount(),
                projection.getOldAmount(),
                projection.getAvailableAmount(),
                projection.getStartDate(),
                projection.getEndDate()
        );
    }

    private BenefitDtoResponse toBenefitDto(CardSumaryProjection projection) {
        return new BenefitDtoResponse(
                projection.getHasDiscount(),
                projection.getMultiplierPoints(),
                projection.getTotalPoints()
        );
    }

    public CardDtoResponse toDto(CardSumaryProjection projection) {

        return new CardDtoResponse(
                projection.getTypeCardEnum(),
                projection.getCategoryCardEnum(),
                toBenefitDto(projection),
                toBalanceDto(projection),
                toAccountDto(projection)
        );
    }

    public CardResponse toResponse(CardDtoResponse dto) {
        CardResponseBenefit benefit = new CardResponseBenefit(
                dto.benefit().hasDiscount(),
                dto.benefit().multiplierPoints(),
                dto.benefit().totalPoints()
        );

        CardResponseBalance balance = new CardResponseBalance(
                dto.balance().totalAmount(),
                dto.balance().oldAmount(),
                dto.balance().availableAmount(),
                dto.balance().startDate(),
                dto.balance().endDate()
        );

        CardResponseAccount account = new CardResponseAccount(
                dto.account().creditTotal(),
                dto.account().debtTax(),
                dto.account().currency().name(),
                String.valueOf(dto.account().paymentDate()),
                dto.account().cardStatus().name()
        );

        return new CardResponse(
                dto.typeCard().name(),
                dto.categoryCard().name(),
                benefit,
                balance,
                account
        );
    }
}
