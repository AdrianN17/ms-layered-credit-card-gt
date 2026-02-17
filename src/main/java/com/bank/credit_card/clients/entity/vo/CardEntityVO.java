package com.bank.credit_card.clients.entity.vo;

import com.bank.credit_card.clients.commons.CategoryCard;
import com.bank.credit_card.clients.commons.TypeCard;
import com.bank.credit_card.clients.entity.CardAccountEntity;
import com.bank.credit_card.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Cards")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardEntityVO extends GenericEntity {

    @Id
    @Column(name = "cardId")
    private Long cardId;

    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "typeCard")
    private TypeCard typeCard;

    @Column(name = "numberCard")
    private String numberCard;

    @Column(name = "categoryCard")
    private CategoryCard categoryCard;

    @Column(name = "dateCard")
    private String dateCard;

    @Column(name = "cvv")
    private String cvv;

    @OneToOne(mappedBy = "cardEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CardAccountEntity cardAccount;
}
