package com.bank.credit_card.payment.controller;

import com.bank.credit_card.aop.annotation.TransactionalUseCase;
import com.bank.credit_card.generic.schema.response.DefaultResponse2xx;
import com.bank.credit_card.generic.schema.response.DefaultResponse4xx;
import com.bank.credit_card.generic.schema.response.UUID202Response;
import com.bank.credit_card.payment.schema.request.InitiatePaymentRequest;
import com.bank.credit_card.payment.schema.response.RetrievePayment200Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Validated
@Tag(name = "Payment", description = "the Payment API")
public interface PaymentApi {

    /**
     * POST /CardManagement/{cardId}/Payment/Initiate : initiatePayment
     */
    @TransactionalUseCase
    @Operation(
            operationId = "initiatePayment",
            summary = "initiatePayment",
            responses = {
                    @ApiResponse(responseCode = "202", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UUID202Response.class))
                    }),
                    @ApiResponse(responseCode = "400", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultResponse4xx.class))
                    }),
                    @ApiResponse(responseCode = "404", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultResponse2xx.class))
                    }),
                    @ApiResponse(responseCode = "500", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultResponse2xx.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/CardManagement/{cardId}/Payment/Initiate",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<UUID202Response> initiatePayment(
            @Parameter(name = "cardId", required = true, in = ParameterIn.PATH) @PathVariable("cardId") Long cardId,
            @Parameter(name = "CreatePaymentRequest", required = true) @Valid @RequestBody InitiatePaymentRequest initiatePaymentRequest,
            BindingResult bindingResult
    );

    /**
     * PUT /CardManagement/{cardId}/Payment/{paymentId}/Control : controlPayment
     */
    @TransactionalUseCase
    @Operation(
            operationId = "controlPayment",
            summary = "controlPayment",
            responses = {
                    @ApiResponse(responseCode = "202", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UUID202Response.class))
                    }),
                    @ApiResponse(responseCode = "400", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultResponse4xx.class))
                    }),
                    @ApiResponse(responseCode = "404", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultResponse2xx.class))
                    }),
                    @ApiResponse(responseCode = "500", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultResponse2xx.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/CardManagement/{cardId}/Payment/{paymentId}/Control",
            produces = {"application/json"}
    )
    ResponseEntity<UUID202Response> controlPayment(
            @Parameter(name = "cardId", required = true, in = ParameterIn.PATH) @PathVariable("cardId") Long cardId,
            @Parameter(name = "paymentId", required = true, in = ParameterIn.PATH) @PathVariable("paymentId") UUID paymentId
    );

    /**
     * GET /CardManagement/{cardId}/Payment/Retrieve : retrievePayment
     */
    @Operation(
            operationId = "retrievePayment",
            summary = "retrievePayment",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = RetrievePayment200Response.class))
                    }),
                    @ApiResponse(responseCode = "400", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultResponse4xx.class))
                    }),
                    @ApiResponse(responseCode = "404", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultResponse2xx.class))
                    }),
                    @ApiResponse(responseCode = "500", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultResponse2xx.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/CardManagement/{cardId}/Payment/Retrieve",
            produces = {"application/json"}
    )
    ResponseEntity<RetrievePayment200Response> retrievePayment(
            @Parameter(name = "cardId", required = true, in = ParameterIn.PATH) @PathVariable("cardId") Long cardId,
            @NotNull @Parameter(name = "dateStart", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "dateStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
            @NotNull @Parameter(name = "dateEnd", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "dateEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd
    );
}

