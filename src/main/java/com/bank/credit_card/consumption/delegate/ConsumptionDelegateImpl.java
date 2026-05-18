package com.bank.credit_card.consumption.delegate;

import com.bank.credit_card.balance.service.BalanceService;
import com.bank.credit_card.benefit.service.BenefitService;
import com.bank.credit_card.card.enums.CategoryCardEnum;
import com.bank.credit_card.card.service.CardService;
import com.bank.credit_card.consumption.mapper.ConsumptionMapper;
import com.bank.credit_card.consumption.schema.request.ExchangeConsumptionRequest;
import com.bank.credit_card.consumption.schema.request.InitiateConsumptionRequest;
import com.bank.credit_card.consumption.schema.response.RetrieveConsumption200Response;
import com.bank.credit_card.consumption.service.ConsumptionService;
import com.bank.credit_card.currency.service.CurrencyService;
import com.bank.credit_card.generic.schema.response.UUID202Response;
import com.bank.credit_card.generic.schema.response.UUIDList202Response;
import com.bank.credit_card.generic.util.MapperResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.UUID;

import static com.bank.credit_card.balance.enums.BalanceUseCaseEnum.CONSUMPTION;

@Component
@AllArgsConstructor
public class ConsumptionDelegateImpl implements ConsumptionDelegate {

    private final BenefitService benefitService;
    private final BalanceService balanceService;
    private final CardService cardService;
    private final ConsumptionService consumptionService;
    private final ConsumptionMapper consumptionMapper;
    private final CurrencyService currencyService;

    @Override
    public ResponseEntity<UUID202Response> initiateConsumption(Long cardId,
                                                                InitiateConsumptionRequest initiateConsumptionRequest,
                                                                BindingResult bindingResult) {

        var data = initiateConsumptionRequest.getData();
        var dto = consumptionMapper.toDto(data, cardId);
        var dtoCard = cardService.find(cardId);

        cardService.validate(dtoCard.account().cardStatus());
        consumptionService.save(dto);

        var amountCurrencyCalculate = currencyService.get(
                dtoCard.account().currency(),
                dto.currency(),
                dto.amount());

        benefitService.accumulate(amountCurrencyCalculate,
                cardService.getRatio(dtoCard.categoryCard()),
                cardId);

        balanceService.isOvercharged(cardId);

        return MapperResponse.getUUID202Response(dto.consumptionId());
    }

    @Override
    public ResponseEntity<UUID202Response> controlConsumption(Long cardId, UUID consumptionId) {

        var consumption = consumptionService.get(consumptionId);
        consumptionService.delete(consumptionId);

        var dtoCard = cardService.find(cardId);

        var amountCurrencyCalculate = currencyService.get(
                dtoCard.account().currency(),
                consumption.currency(),
                consumption.amount());

        balanceService.cancel(cardId, amountCurrencyCalculate, CONSUMPTION);

        balanceService.isOvercharged(cardId);

        return MapperResponse.getUUID202Response(consumptionId);
    }

    @Override
    public ResponseEntity<UUIDList202Response> exchangeConsumption(Long cardId,
                                                                    UUID consumptionId,
                                                                    ExchangeConsumptionRequest exchangeConsumptionRequest,
                                                                    BindingResult bindingResult) {
        var dtoCard = cardService.find(cardId);

        var consumption = consumptionService.get(consumptionId);
        consumptionService.delete(consumptionId);

        var amountCurrencyCalculate = currencyService.get(
                dtoCard.account().currency(),
                consumption.currency(),
                consumption.amount());

        balanceService.cancel(cardId, amountCurrencyCalculate, CONSUMPTION);

        cardService.validate(dtoCard.account().cardStatus());

        var consumptionsSplit = consumptionService.split(exchangeConsumptionRequest.getData().getInstallments(),
                cardId.toString(),
                consumptionId);

        var consumptionsIds = consumptionsSplit.stream().map(consumptionRequestDto -> {

            var amountConsumptionCurrencyCalculate = currencyService.get(
                    dtoCard.account().currency(),
                    consumptionRequestDto.currency(),
                    consumptionRequestDto.amount());

            balanceService.apply(cardId, amountConsumptionCurrencyCalculate, CONSUMPTION);
            consumptionService.save(consumptionRequestDto);

            return consumptionRequestDto.consumptionId();
        }).toList();

        cardService.validate(dtoCard.account().cardStatus());

        balanceService.isOvercharged(cardId);

        return MapperResponse.getUUIDList202Response(consumptionsIds);
    }

    @Override
    public ResponseEntity<RetrieveConsumption200Response> retrieveConsumption(Long cardId,
                                                                               LocalDate dateStart,
                                                                               LocalDate dateEnd) {
        var consumptionsResponse = consumptionService.findAll(cardId.toString(), dateStart, dateEnd)
                .stream()
                .map(consumptionMapper::toResponse)
                .toList();

        return MapperResponse.getConsumptionResponse(consumptionsResponse);
    }
}

