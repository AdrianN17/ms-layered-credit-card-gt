package com.bank.credit_card.generic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.bank.credit_card.balance.repository",
        "com.bank.credit_card.card.repository",
        "com.bank.credit_card.benefit.repository"
})
public class JpaRepositoryConfig {
}

