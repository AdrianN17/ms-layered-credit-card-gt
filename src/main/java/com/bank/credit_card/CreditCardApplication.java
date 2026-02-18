package com.bank.credit_card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableFeignClients
public class CreditCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditCardApplication.class, args);
    }

}
