package com.bank.credit_card.generic.util;

import com.bank.credit_card.generic.schema.response.Tracking;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static com.bank.credit_card.generic.util.GenericDateUtility.getCurrentLocalDateTime;

@UtilityClass
public class GenericResponsesUtility {

    public static ResponseEntity<Tracking> generateTracking() {
        var tracking = new Tracking(UUID.randomUUID(), getCurrentLocalDateTime());
        return ResponseEntity.accepted().body(tracking);
    }
}
