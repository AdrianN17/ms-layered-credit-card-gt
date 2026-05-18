package com.bank.credit_card.payment.mapper;

import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.generic.mapper.EntityMapper;
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

import java.time.ZoneOffset;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class PaymentMapper implements EntityMapper<PaymentRequestDto, PaymentEntity>,
                                      ResponseMapper<PaymentResponseDto, PaymentResponse> {

    private final Environment environment;

    public PaymentRequestDto toDto(PaymentRequest request, Long cardId) {
        return PaymentRequestDto.of(
                ChannelPaymentEnum.valueOf(request.getChannel()),
                CurrencyEnum.valueOf(request.getCurrency()),
                request.getAmount(),
                CategoryPaymentEnum.valueOf(request.getCategory()),
                request.getPointsUsed(),
                cardId
        );
    }

    public PaymentEntity toEntity(PaymentRequestDto dto) {
        boolean isNew = Arrays.asList(environment.getActiveProfiles()).contains("new");

        if (isNew) {
            return PaymentEntityCosmos.builder()
                    .cardId(dto.cardId().toString())
                    .channel(dto.channel())
                    .currency(dto.currency())
                    .amount(dto.amount())
                    .category(dto.category())
                    .build();
        } else {
            return PaymentEntityMongo.builder()
                    .cardId(dto.cardId().toString())
                    .channel(dto.channel())
                    .currency(dto.currency())
                    .amount(dto.amount())
                    .category(dto.category())
                    .build();
        }
    }

    public PaymentResponseDto toDto(PaymentEntity entity) {
        return new PaymentResponseDto(
                entity.getChannel(),
                entity.getCurrency(),
                entity.getAmount(),
                entity.getCategory(),
                entity.getPaymentDate(),
                entity.getPaymentApprobationDate()
        );
    }

    public PaymentResponse toResponse(PaymentResponseDto dto) {
        return new PaymentResponse(
                dto.channel().name(),
                dto.currency().name(),
                dto.amount(),
                dto.category().name(),
                dto.paymentDate().atOffset(ZoneOffset.UTC),
                dto.paymentApprobationDate().atOffset(ZoneOffset.UTC)
        );
    }

}
