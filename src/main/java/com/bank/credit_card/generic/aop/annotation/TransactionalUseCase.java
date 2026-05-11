package com.bank.credit_card.generic.aop.annotation;

import com.bank.credit_card.generic.aop.aspect.TransactionalUseCaseAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a use-case service method (or class) so that the
 * {@link TransactionalUseCaseAspect}
 * wraps its execution inside a database transaction.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionalUseCase {
}

