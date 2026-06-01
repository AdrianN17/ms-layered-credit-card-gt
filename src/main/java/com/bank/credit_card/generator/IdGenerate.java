package com.bank.credit_card.generator;


import java.util.Optional;

@FunctionalInterface
public interface IdGenerate {
    Optional<Long> load();
}