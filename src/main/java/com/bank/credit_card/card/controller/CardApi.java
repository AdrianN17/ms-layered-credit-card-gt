package com.bank.credit_card.card.controller;

import com.bank.credit_card.card.schema.request.InitiateCardRequest;
import com.bank.credit_card.card.schema.response.RetrieveBalance200Response;
import com.bank.credit_card.generic.aop.annotation.TransactionalUseCase;
import com.bank.credit_card.generic.schema.response.DefaultResponse2xx;
import com.bank.credit_card.generic.schema.response.DefaultResponse4xx;
import com.bank.credit_card.generic.schema.response.Long202Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Tag(name = "Card", description = "the Card API")
public interface CardApi {

    /**
     * POST /CardManagement/Initiate : initiateCard
     *
     * @param initiateCardRequest (required)
     * @return (status code 202)
     */
    @TransactionalUseCase
    @Operation(
            operationId = "initiateCard",
            summary = "initiateCard",
            responses = {
                    @ApiResponse(responseCode = "202", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Long202Response.class))
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
            value = "/CardManagement/Initiate",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<Long202Response> initiateCard(
            @Parameter(name = "InitiateCardRequest", required = true) @Valid @RequestBody InitiateCardRequest initiateCardRequest,
            BindingResult bindingResult
    );

    /**
     * PUT /CardManagement/{cardId}/Control : controlCard
     *
     * @param cardId (required)
     * @return (status code 202)
     */
    @TransactionalUseCase
    @Operation(
            operationId = "controlCard",
            summary = "controlCard",
            responses = {
                    @ApiResponse(responseCode = "202", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Long202Response.class))
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
            value = "/CardManagement/{cardId}/Control",
            produces = {"application/json"}
    )
    ResponseEntity<Long202Response> controlCard(
            @Parameter(name = "cardId", required = true, in = ParameterIn.PATH) @PathVariable("cardId") Long cardId
    );

    /**
     * GET /CardManagement/{cardId}/Retrieve : retrieveBalance
     *
     * @param cardId (required)
     * @return (status code 200)
     */
    @Operation(
            operationId = "retrieveBalance",
            summary = "retrieveBalance",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = RetrieveBalance200Response.class))
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
            value = "/CardManagement/{cardId}/Retrieve",
            produces = {"application/json"}
    )
    ResponseEntity<RetrieveBalance200Response> retrieveBalance(
            @Parameter(name = "cardId", required = true, in = ParameterIn.PATH) @PathVariable("cardId") Long cardId
    );
}

