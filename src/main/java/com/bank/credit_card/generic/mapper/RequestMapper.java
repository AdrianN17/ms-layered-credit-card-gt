package com.bank.credit_card.generic.mapper;

/**
 * Maps a schema Request object into a domain DTO.
 *
 * @param <R> Request  (schema layer)
 * @param <D> DTO      (domain layer)
 */
public interface RequestMapper<R, D> {
    D toDto(R request);
}

