package com.bank.credit_card.clients.entity;

import com.bank.credit_card.clients.commons.CategoryCard;
import com.bank.credit_card.clients.commons.TypeCard;
import com.bank.credit_card.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "typeCard")
    private TypeCard typeCard;

    @Column(name = "numberCard")
    private String numberCard;

    @Column(name = "categoryCard")
    private CategoryCard categoryCard;

    @Column(name = "cvv")
    private String cvv;

    @OneToOne(mappedBy = "cardEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CardAccountEntity cardAccount;

    public CardEntity duplicate() {
        CardEntity copy = CardEntity.builder()
                .typeCard(this.typeCard)
                .numberCard(this.numberCard)
                .categoryCard(this.categoryCard)
                .cvv(this.cvv)
                .build();

        if (!isNull(this.cardAccount)) {
            CardAccountEntity accountCopy = this.cardAccount.duplicate();
            copy.setCardAccount(accountCopy);
        }

        copy.setCreatedDate(this.getCreatedDate());
        copy.setUpdatedDate(this.getUpdatedDate());
        copy.setStatus(this.getStatus());

        return copy;
    }

}
