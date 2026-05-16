package com.bank.credit_card.generic.util;

import com.bank.credit_card.card.schema.response.CardResponse;
import com.bank.credit_card.card.schema.response.RetrieveBalance200Response;
import com.bank.credit_card.consumption.schema.response.ConsumptionResponse;
import com.bank.credit_card.consumption.schema.response.RetrieveConsumption200Response;
import com.bank.credit_card.generic.schema.response.*;
import com.bank.credit_card.payment.schema.response.PaymentResponse;
import com.bank.credit_card.payment.schema.response.RetrievePayment200Response;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class MapperResponse {
    public static ResponseEntity<Long202Response> getLong202Response(Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                new Long202Response(new Tracking(UUID.randomUUID(),
                        OffsetDateTime.now()), new LongResponse(id)));
    }

    public static ResponseEntity<UUID202Response> getUUID202Response(UUID id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UUID202Response(new Tracking(UUID.randomUUID(), OffsetDateTime.now()),
                new UUIDResponse(id)));
    }

    public static ResponseEntity<UUIDList202Response> getUUIDList202Response(List<UUID> ids) {
        List<UUIDResponse> data = ids.stream().map(UUIDResponse::new).toList();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UUIDList202Response(new Tracking(UUID.randomUUID(), OffsetDateTime.now()), data));
    }

    public static ResponseEntity<RetrieveBalance200Response> getRetrieveBalance(CardResponse response) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RetrieveBalance200Response(response, new Tracking(UUID.randomUUID(), OffsetDateTime.now())));
    }

    public static ResponseEntity<RetrievePayment200Response> getPaymentResponse(List<PaymentResponse> response) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RetrievePayment200Response(new Tracking(UUID.randomUUID(), OffsetDateTime.now()), response));
    }

    public static ResponseEntity<RetrieveConsumption200Response> getConsumptionResponse(List<ConsumptionResponse> response) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RetrieveConsumption200Response(new Tracking(UUID.randomUUID(), OffsetDateTime.now()), response));
    }
}
