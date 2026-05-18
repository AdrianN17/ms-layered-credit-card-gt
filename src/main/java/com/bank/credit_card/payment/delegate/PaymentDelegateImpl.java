package com.bank.credit_card.payment.delegate;

import com.bank.credit_card.balance.service.BalanceService;
import com.bank.credit_card.benefit.service.BenefitService;
import com.bank.credit_card.card.service.CardService;
import com.bank.credit_card.currency.service.CurrencyService;
import com.bank.credit_card.generic.schema.response.UUID202Response;
import com.bank.credit_card.generic.util.MapperResponse;
import com.bank.credit_card.payment.mapper.PaymentMapper;
import com.bank.credit_card.payment.schema.request.InitiatePaymentRequest;
import com.bank.credit_card.payment.schema.response.RetrievePayment200Response;
import com.bank.credit_card.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.UUID;

import static com.bank.credit_card.balance.enums.BalanceUseCaseEnum.PAYMENT;
import static java.util.Objects.isNull;

@Component
@AllArgsConstructor
public class PaymentDelegateImpl implements PaymentDelegate {

    private final BenefitService benefitService;
    private final BalanceService balanceService;
    private final CardService cardService;
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;
    private final CurrencyService currencyService;

    @Override
    public ResponseEntity<UUID202Response> initiatePayment(Long cardId,
                                                            InitiatePaymentRequest initiatePaymentRequest,
                                                            BindingResult bindingResult) {

        var data = initiatePaymentRequest.getData();

        var amount = data.getAmount();

        if (!isNull(data.getPointsUsed()))
        {
            amount = benefitService.discount(data.getAmount(), data.getPointsUsed(), cardId);
            data.setAmount(amount);
        }

        var dto = paymentMapper.toDto(data, cardId);
        var dtoCard = cardService.find(cardId);

        paymentService.validate(
                dtoCard.balance().availableAmount(),
                dtoCard.balance().totalAmount(),
                dtoCard.balance().startDate(),
                dtoCard.balance().endDate(),
                dto
        );

        var amountCurrencyCalculate = currencyService.get(
                dtoCard.account().currency(),
                dto.currency(),
                dto.amount());

        balanceService.apply(cardId,
                amountCurrencyCalculate
                , PAYMENT);

        paymentService.save(dto);

        balanceService.isOvercharged(cardId);

        return MapperResponse.getUUID202Response(dto.paymentId());
    }

    @Override
    public ResponseEntity<UUID202Response> controlPayment(Long cardId, UUID paymentId) {

        var payment = paymentService.get(paymentId);
        paymentService.delete(paymentId);
        var dtoCard = cardService.find(cardId);

        var amountCurrencyCalculate = currencyService.get(
                dtoCard.account().currency(),
                payment.currency(),
                payment.amount());

        balanceService.cancel(cardId, amountCurrencyCalculate, PAYMENT);

        balanceService.isOvercharged(cardId);

        return MapperResponse.getUUID202Response(paymentId);
    }

    @Override
    public ResponseEntity<RetrievePayment200Response> retrievePayment(Long cardId,
                                                                       LocalDate dateStart,
                                                                       LocalDate dateEnd) {

        var paymentsResponse = paymentService.findAll(cardId.toString(), dateStart, dateEnd)
                .stream()
                .map(paymentMapper::toResponse)
                .toList();

        return MapperResponse.getPaymentResponse(paymentsResponse);
    }
}

