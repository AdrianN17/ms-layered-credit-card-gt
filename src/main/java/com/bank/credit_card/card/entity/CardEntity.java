package com.bank.credit_card.card.entity;

import com.bank.credit_card.card.converter.CategoryCardEnumConverter;
import com.bank.credit_card.card.converter.TypeCardEnumConverter;
import com.bank.credit_card.card.enums.CategoryCardEnum;
import com.bank.credit_card.card.enums.TypeCardEnum;
import com.bank.credit_card.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Cards")
public class CardEntity extends GenericEntity {

    @Id
    @Column(name = "cardId", nullable = false)
    private Long cardId;

    @Convert(converter = TypeCardEnumConverter.class)
    @Column(name = "typeCard", updatable = false)
    private TypeCardEnum typeCard;

    @Convert(converter = CategoryCardEnumConverter.class)
    @Column(name = "categoryCard", updatable = false)
    private CategoryCardEnum categoryCard;
}
