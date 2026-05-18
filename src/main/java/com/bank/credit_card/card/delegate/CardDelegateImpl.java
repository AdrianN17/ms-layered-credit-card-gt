package com.bank.credit_card.card.delegate;

import com.bank.credit_card.balance.mapper.BalanceMapper;
import com.bank.credit_card.balance.service.BalanceService;
import com.bank.credit_card.benefit.mapper.BenefitMapper;
import com.bank.credit_card.benefit.service.BenefitService;
import com.bank.credit_card.card.mapper.CardMapper;
import com.bank.credit_card.card.mapper.CardSummaryMapper;
import com.bank.credit_card.card.schema.request.InitiateCardRequest;
import com.bank.credit_card.card.schema.response.RetrieveBalance200Response;
import com.bank.credit_card.card.service.CardService;
import com.bank.credit_card.generic.schema.response.Long202Response;
import com.bank.credit_card.generic.util.MapperResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import static com.bank.credit_card.generic.util.BindingValidator.validate;

@Component
@AllArgsConstructor
public class CardDelegateImpl implements CardDelegate {

    private final CardService cardService;
    private final BalanceService balanceService;
    private final BenefitService benefitService;
    private final CardMapper cardMapper;
    private final BalanceMapper balanceMapper;
    private final BenefitMapper benefitMapper;
    private final CardSummaryMapper cardSummaryMapper;

    @Override
    public ResponseEntity<Long202Response> initiateCard(InitiateCardRequest initiateCardRequest,
                                                         BindingResult bindingResult) {
        validate(bindingResult);
        var dto = cardMapper.toDto(initiateCardRequest.getData());
        Long id = cardService.save(dto);

        var dtoBenefit = benefitMapper.toDto(initiateCardRequest.getData().getBenefit(), id);
        benefitService.save(dtoBenefit, id);

        var dtoBalance = balanceMapper.toDto(initiateCardRequest.getData().getAccount(), id);
        balanceService.save(dtoBalance, id);

        return MapperResponse.getLong202Response(id);
    }

    @Override
    public ResponseEntity<Long202Response> controlCard(Long cardId) {
        var dtoCard = cardService.find(cardId);
        cardService.validate(dtoCard.account().cardStatus());
        cardService.delete(cardId);

        return MapperResponse.getLong202Response(cardId);
    }

    @Override
    public ResponseEntity<RetrieveBalance200Response> retrieveBalance(Long cardId) {
        var dtoCard = cardService.find(cardId);
        var response = cardSummaryMapper.toResponse(dtoCard);

        return MapperResponse.getRetrieveBalance(response);
    }
}
