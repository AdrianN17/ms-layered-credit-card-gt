package com.bank.credit_card.consumptions.entity;

import com.bank.credit_card.generic.commons.Currency;
import com.bank.credit_card.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Consumptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumptionEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumptionId")
    private Long consumptionId;

    @Column(name = "cardId")
    private Long cardId;

    @Column(name = "sellerName")
    private String sellerName;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "consumptionDate")
    private LocalDateTime consumptionDate;

    @Column(name = "consumptionApprobationDae")
    private LocalDateTime consumptionApprobationDae;


}

