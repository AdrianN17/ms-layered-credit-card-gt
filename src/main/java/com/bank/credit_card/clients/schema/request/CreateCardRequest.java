package com.bank.credit_card.clients.schema.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * CreateCardRequest
 */

@JsonTypeName("createCard_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-15T00:06:49.687636300-05:00[America/Lima]")
public class CreateCardRequest {

    private CardRequest card;

    private CardAccountRequest cardAccount;

    public CreateCardRequest() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public CreateCardRequest(CardRequest card, CardAccountRequest cardAccount) {
        this.card = card;
        this.cardAccount = cardAccount;
    }

    public CreateCardRequest card(CardRequest card) {
        this.card = card;
        return this;
    }

    /**
     * Get card
     *
     * @return card
     */
    @NotNull(message = "card is required")
    @Valid
    @Schema(name = "card", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("card")
    public CardRequest getCard() {
        return card;
    }

    public void setCard(CardRequest card) {
        this.card = card;
    }

    public CreateCardRequest cardAccount(CardAccountRequest cardAccount) {
        this.cardAccount = cardAccount;
        return this;
    }

    /**
     * Get cardAccount
     *
     * @return cardAccount
     */
    @NotNull(message = "cardAccount is required")
    @Valid
    @Schema(name = "cardAccount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("cardAccount")
    public CardAccountRequest getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(CardAccountRequest cardAccount) {
        this.cardAccount = cardAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateCardRequest createCardRequest = (CreateCardRequest) o;
        return Objects.equals(this.card, createCardRequest.card) &&
                Objects.equals(this.cardAccount, createCardRequest.cardAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card, cardAccount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateCardRequest {\n");
        sb.append("    card: ").append(toIndentedString(card)).append("\n");
        sb.append("    cardAccount: ").append(toIndentedString(cardAccount)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
