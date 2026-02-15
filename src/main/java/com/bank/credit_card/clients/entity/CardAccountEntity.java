package com.bank.credit_card.clients.entity;

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
public class CardAccountEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public CardAccountEntity duplicate() {
        CardAccountEntity copy = CardAccountEntity.builder()
                .crediticialTotalAmount(this.crediticialTotalAmount)
                .debtTax(this.debtTax)
                .currency(this.currency)
                .facturationDate(this.facturationDate)
                .paymentDate(this.paymentDate)
                .build();

        copy.setStatus(this.getStatus());
        copy.setCreatedDate(this.getCreatedDate());
        copy.setUpdatedDate(this.getUpdatedDate());

        return copy;
    }
}
