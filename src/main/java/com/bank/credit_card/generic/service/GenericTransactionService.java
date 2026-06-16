package com.bank.credit_card.generic.service;

import java.time.LocalDate;
import java.util.List;

public interface GenericTransactionService<PID, ID, RS> {
    List<RS> findAll(PID cardId,
                                     LocalDate start,
                                     LocalDate end);

    RS get(ID id);
}
