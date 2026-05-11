package com.bank.credit_card.consumption.controller;

import com.bank.credit_card.aop.annotation.TransactionalUseCase;
import com.bank.credit_card.consumption.schema.request.ExchangeConsumptionRequest;
import com.bank.credit_card.consumption.schema.request.InitiateConsumptionRequest;
import com.bank.credit_card.consumption.schema.response.RetrieveConsumption200Response;
import com.bank.credit_card.generic.schema.response.DefaultResponse2xx;
import com.bank.credit_card.generic.schema.response.DefaultResponse4xx;
import com.bank.credit_card.generic.schema.response.UUID202Response;
import com.bank.credit_card.generic.schema.response.UUIDList202Response;
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
@Tag(name = "Consumption", description = "the Consumption API")
public interface ConsumptionApi {

    /**
     * POST /CardManagement/{cardId}/Consumption/Initiate : initiateConsumption
     */
    @TransactionalUseCase
    @Operation(
            operationId = "initiateConsumption",
            summary = "initiateConsumption",
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
            value = "/CardManagement/{cardId}/Consumption/Initiate",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<UUID202Response> initiateConsumption(
            @Parameter(name = "cardId", required = true, in = ParameterIn.PATH) @PathVariable("cardId") Long cardId,
            @Parameter(name = "ConsumptionRequest", required = true) @Valid @RequestBody InitiateConsumptionRequest initiateConsumptionRequest,
            BindingResult bindingResult
    );

    /**
     * PUT /CardManagement/{cardId}/Consumption/{ConsumptionId}/Control : controlConsumption
     */
    @TransactionalUseCase
    @Operation(
            operationId = "controlConsumption",
            summary = "controlConsumption",
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
            value = "/CardManagement/{cardId}/Consumption/{ConsumptionId}/Control",
            produces = {"application/json"}
    )
    ResponseEntity<UUID202Response> controlConsumption(
            @Parameter(name = "cardId", required = true, in = ParameterIn.PATH) @PathVariable("cardId") Long cardId,
            @Parameter(name = "ConsumptionId", required = true, in = ParameterIn.PATH) @PathVariable("ConsumptionId") UUID consumptionId
    );

    /**
     * PUT /CardManagement/{cardId}/Consumption/{ConsumptionId}/Exchange : exchangeConsumption
     */
    @TransactionalUseCase
    @Operation(
            operationId = "exchangeConsumption",
            summary = "exchangeConsumption",
            responses = {
                    @ApiResponse(responseCode = "202", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UUIDList202Response.class))
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
            value = "/CardManagement/{cardId}/Consumption/{ConsumptionId}/Exchange",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<UUIDList202Response> exchangeConsumption(
            @Parameter(name = "cardId", required = true, in = ParameterIn.PATH) @PathVariable("cardId") Long cardId,
            @Parameter(name = "ConsumptionId", required = true, in = ParameterIn.PATH) @PathVariable("ConsumptionId") UUID consumptionId,
            @Parameter(name = "ExchangeConsumptionRequest", required = true) @Valid @RequestBody ExchangeConsumptionRequest exchangeConsumptionRequest,
            BindingResult bindingResult
    );

    /**
     * GET /CardManagement/{cardId}/Consumption/Retrieve : retrieveConsumption
     */
    @Operation(
            operationId = "retrieveConsumption",
            summary = "retrieveConsumption",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = RetrieveConsumption200Response.class))
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
            value = "/CardManagement/{cardId}/Consumption/Retrieve",
            produces = {"application/json"}
    )
    ResponseEntity<RetrieveConsumption200Response> retrieveConsumption(
            @Parameter(name = "cardId", required = true, in = ParameterIn.PATH) @PathVariable("cardId") Long cardId,
            @NotNull @Parameter(name = "dateStart", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "dateStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
            @NotNull @Parameter(name = "dateEnd", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "dateEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd
    );
}

