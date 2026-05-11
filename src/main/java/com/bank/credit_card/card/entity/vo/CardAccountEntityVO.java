package com.bank.credit_card.card.entity.vo;

import com.bank.credit_card.card.converter.CardStatusEnumConverter;
import com.bank.credit_card.card.enums.CardStatusEnum;
import com.bank.credit_card.generic.converter.CurrencyEnumConverter;
import com.bank.credit_card.generic.entity.GenericEntity;
import com.bank.credit_card.generic.enums.CurrencyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "CardAccounts")
public class CardAccountEntityVO extends GenericEntity {

    @Id
    @Column(name = "cardAccountId", nullable = false)
    private Long cardAccountId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardId", nullable = false)
    private CardEntityVO card;

    @Column(name = "creditTotal", precision = 19, scale = 2)
    private BigDecimal creditTotal;

    @Column(name = "debtTax", precision = 19, scale = 2)
    private BigDecimal debtTax;

    @Convert(converter = CurrencyEnumConverter.class)
    @Column(name = "currency")
    private CurrencyEnum currency;

    @Column(name = "paymentDate")
    private Short paymentDate;

    @Convert(converter = CardStatusEnumConverter.class)
    @Column(name = "cardStatus")
    private CardStatusEnum cardStatus;
}
