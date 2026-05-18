package com.bank.credit_card.generic.mapper;

/**
 * Maps a domain DTO into a schema Response object.
 *
 * @param <D>  DTO      (domain layer)
 * @param <RS> Response (schema layer)
 */
public interface ResponseMapper<D, RS> {
    RS toResponse(D dto);
}

