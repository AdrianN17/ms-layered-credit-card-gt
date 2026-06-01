package com.bank.credit_card.generic.mapper;

/**
 * Maps a schema Request object into a domain DTO.
 *
 * @param <R> Request  (schema layer)
 * @param <D> DTO      (domain layer)
 */
@FunctionalInterface
public interface RequestIDDtoMapper<R, D> {
    D toRequestDto(R request, Long cardId, Long Id);
}

