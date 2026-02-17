package com.bank.credit_card.clients.entity.vo;

import com.bank.credit_card.generic.commons.Currency;
import com.bank.credit_card.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "CardAccounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardAccountEntityVO extends GenericEntity {

    @Id
    @Column(name = "cardAccountId")
    private Long cardAccountId;

    @Column(name = "crediticialTotalAmount")
    private BigDecimal crediticialTotalAmount;

    @Column(name = "debtTax")
    private BigDecimal debtTax;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "facturationDate")
    private Short facturationDate;

    @Column(name = "paymentDate")
    private Short paymentDate;
}
