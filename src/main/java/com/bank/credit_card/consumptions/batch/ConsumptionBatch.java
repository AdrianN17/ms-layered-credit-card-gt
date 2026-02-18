package com.bank.credit_card.consumptions.batch;

import com.bank.credit_card.consumptions.service.ConsumptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.bank.credit_card.generic.util.GenericDateUtility.getCurrentLocalDate;
import static com.bank.credit_card.generic.util.GenericDateUtility.getCurrentLocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumptionBatch {

    private final ConsumptionService consumptionService;

    @Scheduled(cron = "0 0 3 * * *")
    public void executeBatch() {
        log.info("Starting payment batch execution");
        var dateTime = getCurrentLocalDateTime();
        var date = getCurrentLocalDate();
        consumptionService.approbate(date, dateTime);
        log.info("Finished payment batch execution");

    }

}
