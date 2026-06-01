package com.bank.credit_card.payment.mapper;

import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.generic.enums.StatusEnum;
import com.bank.credit_card.generic.mapper.EntityMapper;
import com.bank.credit_card.generic.mapper.RequestDtoMapper;
import com.bank.credit_card.generic.mapper.ResponseDtoMapper;
import com.bank.credit_card.generic.mapper.ResponseMapper;
import com.bank.credit_card.payment.dto.PaymentRequestDto;
import com.bank.credit_card.payment.dto.PaymentResponseDto;
import com.bank.credit_card.payment.entity.PaymentEntity;
import com.bank.credit_card.payment.entity.PaymentEntityCosmos;
import com.bank.credit_card.payment.entity.PaymentEntityMongo;
import com.bank.credit_card.payment.enums.CategoryPaymentEnum;
import com.bank.credit_card.payment.enums.ChannelPaymentEnum;
import com.bank.credit_card.payment.schema.request.PaymentRequest;
import com.bank.credit_card.payment.schema.response.PaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Objects;

import static com.bank.credit_card.payment.exception.PaymentErrorMessage.PAYMENT_APPROBATION_DATE_NOT_NULL;
import static com.bank.credit_card.payment.exception.PaymentErrorMessage.PAYMENT_DATE_NOT_NULL;

@Component
@AllArgsConstructor
public class PaymentMapper implements
        ResponseDtoMapper<PaymentResponseDto, PaymentEntity>,
        EntityMapper<PaymentRequestDto, PaymentEntity>,
        RequestDtoMapper<PaymentRequest, PaymentRequestDto>,
        ResponseMapper<PaymentResponseDto, PaymentResponse> {

    private final Environment environment;

    @Override
    public PaymentRequestDto toRequestDto(PaymentRequest request, Long cardId) {
        return PaymentRequestDto.of(
                ChannelPaymentEnum.ofCode(request.getChannel()),
                CurrencyEnum.ofCode(request.getCurrency()),
                request.getAmount(),
                CategoryPaymentEnum.ofCode(request.getCategory()),
                request.getPointsUsed(),
                cardId
        );
    }

    @Override
    public PaymentEntity toEntity(PaymentRequestDto dto) {
        boolean isNew = Arrays.asList(environment.getActiveProfiles()).contains("new");

        if (isNew) {
            return PaymentEntityCosmos.builder()
                    .paymentId(dto.paymentId())
                    .cardId(dto.cardId().toString())
                    .channel(dto.channel())
                    .currency(dto.currency())
                    .amount(dto.amount())
                    .category(dto.category())
                    .paymentDate(LocalDateTime.now())
                    .paymentApprobationDate(LocalDateTime.now())
                    .status(StatusEnum.ACTIVE)
                    .createdDate(LocalDateTime.now())
                    .build();
        } else {
            return PaymentEntityMongo.builder()
                    .paymentId(dto.paymentId())
                    .cardId(dto.cardId().toString())
                    .channel(dto.channel())
                    .currency(dto.currency())
                    .amount(dto.amount())
                    .category(dto.category())
                    .paymentDate(LocalDateTime.now())
                    .paymentApprobationDate(LocalDateTime.now())
                    .status(StatusEnum.ACTIVE)
                    .createdDate(LocalDateTime.now())
                    .build();
        }
    }

    @Override
    public PaymentResponseDto toResponseDto(PaymentEntity entity) {
        return new PaymentResponseDto(
                entity.getChannel(),
                entity.getCurrency(),
                entity.getAmount(),
                entity.getCategory(),
                entity.getPaymentDate(),
                entity.getPaymentApprobationDate()
        );
    }

    @Override
    public PaymentResponse toResponse(PaymentResponseDto dto) {
        return new PaymentResponse(
                dto.channel().name(),
                dto.currency().name(),
                dto.amount(),
                dto.category().name(),
                Objects.requireNonNull(dto.paymentDate(),             PAYMENT_DATE_NOT_NULL).atOffset(ZoneOffset.UTC),
                Objects.requireNonNull(dto.paymentApprobationDate(),  PAYMENT_APPROBATION_DATE_NOT_NULL).atOffset(ZoneOffset.UTC)
        );
    }

}
