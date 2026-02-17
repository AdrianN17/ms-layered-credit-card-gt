package com.bank.credit_card.balances.entity;

import com.bank.credit_card.generic.commons.Currency;
import com.bank.credit_card.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "balances")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BalanceEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBalance")
    private Long idBalance;

    @Column(name = "cardId")
    private Long cardId;

    @Column(name = "totalAmount")
    private BigDecimal totalAmount;

    @Column(name = "availableAmount")
    private BigDecimal availableAmount;

    @Column(name = "oldAmount")
    private BigDecimal oldAmount;

    @Column(name = "consumptionAmount")
    private BigDecimal consumptionAmount;

    @Column(name = "paymentAmount")
    private BigDecimal paymentAmount;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "exchangeRate")
    private BigDecimal exchangeRate;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;
}

