package com.bank.credit_card.generic.mapper;

@FunctionalInterface
public interface ResponseDtoMapper<RD, E> {
    RD toResponseDto(E entity);
}
