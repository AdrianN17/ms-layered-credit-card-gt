package com.bank.credit_card.card.service;

import com.bank.credit_card.card.dto.CardDtoRequest;
import com.bank.credit_card.card.dto.CardDtoResponse;
import com.bank.credit_card.card.entity.CardEntity;
import com.bank.credit_card.card.enums.CardStatusEnum;
import com.bank.credit_card.card.enums.CategoryCardEnum;
import com.bank.credit_card.card.mapper.CardAccountMapper;
import com.bank.credit_card.card.mapper.CardMapper;
import com.bank.credit_card.card.mapper.CardSummaryMapper;
import com.bank.credit_card.card.repository.CardAccountJpaRepository;
import com.bank.credit_card.card.repository.CardJpaRepository;
import com.bank.credit_card.card.repository.vo.CardVOJpaRepository;
import com.bank.credit_card.generic.enums.StatusEnum;
import com.bank.credit_card.generic.exception.UnprocessableEntityException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

import static com.bank.credit_card.benefit.constant.BenefitConstant.*;
import static com.bank.credit_card.card.enums.CardStatusEnum.IN_DEBT;
import static com.bank.credit_card.card.exception.CardErrorMessage.CARD_NOT_FOUND;
import static com.bank.credit_card.card.exception.CardErrorMessage.IN_DEBT_CARD;
import static com.bank.credit_card.generic.util.Validation.isNotConditional;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardJpaRepository cardJpaRepository;
    private final CardAccountJpaRepository cardAccountJpaRepository;
    private final CardVOJpaRepository cardVOJpaRepository;
    private final CardMapper cardMapper;
    private final CardAccountMapper cardAccountMapper;
    private final CardSummaryMapper cardSummaryMapper;

    @Override
    public Long save(CardDtoRequest dto) {
        var cardEntity = cardMapper.toEntity(dto);
        var savedCard = cardJpaRepository.save(cardEntity);

        var cardAccountEntity = cardAccountMapper.toEntity(dto);
        cardAccountJpaRepository.save(cardAccountEntity);

        return savedCard.getCardId();
    }

    @Override
    public CardDtoResponse find(Long id) {
        var card = cardVOJpaRepository.getCardAllProjectionByCardId(id)
                .orElseThrow(() -> new UnprocessableEntityException(CARD_NOT_FOUND));

        return cardSummaryMapper.toResponseDto(card);
    }

    @Override
    public void delete(Long id) {
        CardEntity entity = cardJpaRepository.findById(id)
                .orElseThrow(() -> new UnprocessableEntityException(CARD_NOT_FOUND));
        entity.softDelete();
        cardJpaRepository.save(entity);
    }

    @Override
    public BigDecimal getRatio(CategoryCardEnum categoryCardEnum) {

        return switch (categoryCardEnum) {
            case NORMAL -> RATIO_NORMAL;
            case SILVER -> RATIO_SILVER;
            case GOLD -> RATIO_GOLD;
            case PLATINUM -> RATIO_PLATINUM;
            case BLACK -> RATIO_BLACK;
            case SIGNATURE -> RATIO_SIGNATURE;
            case INFINITY -> RATIO_INFINITY;
        };
    }

    @Override
    public void validate(CardStatusEnum cardStatus) {
        isNotConditional(Objects.equals(cardStatus, IN_DEBT), new UnprocessableEntityException(IN_DEBT_CARD));
    }
}
