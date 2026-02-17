package com.bank.credit_card.clients.entity;

import com.bank.credit_card.clients.commons.CategoryCard;
import com.bank.credit_card.clients.commons.TypeCard;
import com.bank.credit_card.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cards")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}

