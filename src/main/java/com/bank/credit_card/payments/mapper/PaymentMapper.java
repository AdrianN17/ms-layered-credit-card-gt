package com.bank.credit_card.payments.mapper;

import com.bank.credit_card.generic.util.EnumGenericUtility;
import com.bank.credit_card.payments.dto.request.PaymentRequestDto;
import com.bank.credit_card.payments.entity.PaymentEntity;
import com.bank.credit_card.payments.schema.request.PaymentRequest;
import com.bank.credit_card.payments.util.EnumPaymentUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {EnumPaymentUtility.class, EnumGenericUtility.class})
public interface PaymentMapper {

    PaymentEntity toEntity(PaymentRequestDto dto);

    @Mapping(target = "cardId", source = "cardId")
    @Mapping(target = "paymentDate", source = "paymentDate")
    @Mapping(target = "amount", source = "paymentRequest.amount")
    @Mapping(target = "currency", source = "paymentRequest.currency", qualifiedByName = "mapToCurrency")
    @Mapping(target = "channel", source = "paymentRequest.channel", qualifiedByName = "mapToChannelPayment")
    @Mapping(target = "category", source = "paymentRequest.category", qualifiedByName = "mapToCategoryPayment")
    PaymentRequestDto toDto(PaymentRequest paymentRequest, Long cardId, LocalDateTime paymentDate);

}
