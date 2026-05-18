package com.bank.credit_card.generic.mapper;

/**
 * Maps a domain DTO into a persistence Entity.
 *
 * @param <D> DTO    (domain layer)
 * @param <E> Entity (persistence layer)
 */
public interface EntityMapper<D, E> {
    E toEntity(D dto);
}

