package com.bank.credit_card.payment.entity;

import com.bank.credit_card.generic.converter.CurrencyEnumConverter;
import com.bank.credit_card.generic.entity.GenericEntity;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import com.bank.credit_card.payment.enums.CategoryPaymentEnum;
import com.bank.credit_card.payment.enums.ChannelPaymentEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Document(collection = "Payments")
public class PaymentEntityMongo extends GenericEntity implements PaymentEntity {

    @Id
    @Field(targetType = FieldType.STRING)
    private UUID paymentId;

    @Indexed
    @Field("cardId")
    private String cardId;

    private BigDecimal amount;

    @Convert(converter = CurrencyEnumConverter.class)
    private CurrencyEnum currency;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime paymentDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime paymentApprobationDate;

    private ChannelPaymentEnum channel;

    private CategoryPaymentEnum category;
}
