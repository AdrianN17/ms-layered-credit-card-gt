package com.bank.credit_card.card.service;

import com.bank.credit_card.card.dto.CardDtoRequest;
import com.bank.credit_card.card.dto.CardDtoResponse;
import com.bank.credit_card.card.entity.CardEntity;
import com.bank.credit_card.card.mapper.CardAccountMapper;
import com.bank.credit_card.card.mapper.CardMapper;
import com.bank.credit_card.card.mapper.CardSummaryMapper;
import com.bank.credit_card.card.repository.CardAccountJpaRepository;
import com.bank.credit_card.card.repository.CardJpaRepository;
import com.bank.credit_card.card.repository.vo.CardVOJpaRepository;
import com.bank.credit_card.generic.enums.StatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.bank.credit_card.benefit.constant.BenefitConstant.*;
import static com.bank.credit_card.card.enums.CategoryCardEnum.*;

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

        var cardAccountEntity = cardAccountMapper.toEntity(dto, savedCard.getCardId());
        cardAccountJpaRepository.save(cardAccountEntity);

        return savedCard.getCardId();
    }

    @Override
    public CardDtoResponse find(Long id) {

        var card = cardVOJpaRepository.getCardAllProjectionByCardId(id)
                .orElseThrow();

        return cardSummaryMapper.toDto(card);
    }

    @Override
    public void delete(Long id) {
        CardEntity entity = cardJpaRepository.findById(id).orElseThrow();
        entity.setStatus(StatusEnum.INACTIVE);
        cardJpaRepository.save(entity);
    }

    @Override
    public BigDecimal getRatio(Long id) {

        var card = cardVOJpaRepository.getCardAllProjectionByCardId(id)
                .orElseThrow();

        return switch (card.getCategoryCardEnum()) {
            case NORMAL -> RATIO_NORMAL;
            case SILVER -> RATIO_SILVER;
            case GOLD -> RATIO_GOLD;
            case PLATINUM -> RATIO_PLATINUM;
            case BLACK -> RATIO_BLACK;
            case SIGNATURE -> RATIO_SIGNATURE;
            case INFINITY -> RATIO_INFINITY;
        };
    }
}
