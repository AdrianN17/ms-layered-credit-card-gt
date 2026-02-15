package com.bank.credit_card.payments.entity;

import com.bank.credit_card.generic.commons.Currency;
import com.bank.credit_card.generic.entity.GenericEntity;
import com.bank.credit_card.payments.commons.CategoryPayment;
import com.bank.credit_card.payments.commons.ChannelPayment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private Long paymentId;

    @Column(name = "cardId")
    private Long cardId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "paymentDate")
    private LocalDateTime paymentDate;

    @Column(name = "paymentApprobationDate")
    private LocalDateTime paymentApprobationDate;

    @Column(name = "channel")
    private ChannelPayment channel;

    @Column(name = "category")
    private CategoryPayment category;
}

