package com.bank.credit_card.payments.mapper;

import com.bank.credit_card.generic.util.EnumGenericUtility;
import com.bank.credit_card.payments.dto.PaymentDto;
import com.bank.credit_card.payments.entity.PaymentEntity;
import com.bank.credit_card.payments.schema.request.PaymentRequest;
import com.bank.credit_card.payments.util.EnumPaymentUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EnumPaymentUtility.class, EnumGenericUtility.class})
public interface PaymentMapper {

    PaymentEntity toEntity(PaymentDto dto);

    PaymentDto toDto(PaymentEntity entity);

    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "cardId", source = "cardId")
    @Mapping(target = "amount", source="paymentRequest.amount")
    @Mapping(target = "currency", source="paymentRequest.currency", qualifiedByName = "mapToCurrency")
    @Mapping(target = "channel", source="paymentRequest.channel", qualifiedByName = "mapToChannelPayment")
    @Mapping(target = "category", source="paymentRequest.category", qualifiedByName = "mapToCategoryPayment")
    @Mapping(target = "paymentDate", ignore = true)
    @Mapping(target = "paymentApprobationDate", ignore = true)
    PaymentDto toDto(PaymentRequest paymentRequest, Long cardId);

}
