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
import com.bank.credit_card.generator.IdGenerate;
import com.bank.credit_card.generic.exception.UnprocessableEntityException;
import com.bank.credit_card.generic.schema.response.Long202Response;
import com.bank.credit_card.generic.util.MapperResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import static com.bank.credit_card.generator.GeneratorErrorMessage.FAILED_TO_GENERATE_ID;
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
    private final IdGenerate idGenerate;

    @Override
    public ResponseEntity<Long202Response> initiateCard(InitiateCardRequest initiateCardRequest,
                                                         BindingResult bindingResult) {
        validate(bindingResult);

        var cardId = idGenerate.load().orElseThrow(()-> new UnprocessableEntityException(FAILED_TO_GENERATE_ID));
        var cardAccountId = idGenerate.load().orElseThrow(()-> new UnprocessableEntityException(FAILED_TO_GENERATE_ID));
        var dto = cardMapper.toRequestDto(initiateCardRequest.getData(), cardId, cardAccountId);
        cardService.save(dto);

        var benefitId= idGenerate.load().orElseThrow(()-> new UnprocessableEntityException(FAILED_TO_GENERATE_ID));
        var dtoBenefit = benefitMapper.toRequestDto(initiateCardRequest.getData().getBenefit(), cardId, benefitId);

        benefitService.save(dtoBenefit);

        var balanceId = idGenerate.load().orElseThrow(()-> new UnprocessableEntityException(FAILED_TO_GENERATE_ID));
        var dtoBalance = balanceMapper.toRequestDto(initiateCardRequest.getData().getAccount(), cardId, balanceId);

        balanceService.save(dtoBalance);

        return MapperResponse.getLong202Response(cardId);
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
