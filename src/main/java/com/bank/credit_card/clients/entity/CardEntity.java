package com.bank.credit_card.clients.entity;

import com.bank.credit_card.clients.commons.CategoryCard;
import com.bank.credit_card.clients.commons.TypeCard;
import com.bank.credit_card.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.util.Objects.isNull;

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
    private LocalDate dateCard;

    @Column(name = "cvv")
    private String cvv;

    public CardEntity duplicate() {

        return CardEntity.builder()
                .clientId(this.getClientId())
                .typeCard(this.getTypeCard())
                .numberCard(this.getNumberCard())
                .categoryCard(this.getCategoryCard())
                .dateCard(this.getDateCard())
                .cvv(this.getCvv())
                .build();
    }

}
