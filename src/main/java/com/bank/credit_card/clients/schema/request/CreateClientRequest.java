package com.bank.credit_card.clients.schema.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * CreateClientRequest
 */

@JsonTypeName("createClient_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-15T00:06:49.687636300-05:00[America/Lima]")
public class CreateClientRequest {

    private ClientRequest client;

    private CardRequest card;

    private CardAccountRequest cardAccount;

    public CreateClientRequest() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public CreateClientRequest(ClientRequest client, CardRequest card, CardAccountRequest cardAccount) {
        this.client = client;
        this.card = card;
        this.cardAccount = cardAccount;
    }

    public CreateClientRequest client(ClientRequest client) {
        this.client = client;
        return this;
    }

    /**
     * Get client
     *
     * @return client
     */
    @NotNull(message = "client is required")
    @Valid
    @Schema(name = "client", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("client")
    public ClientRequest getClient() {
        return client;
    }

    public void setClient(ClientRequest client) {
        this.client = client;
    }

    public CreateClientRequest card(CardRequest card) {
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

    public CreateClientRequest cardAccount(CardAccountRequest cardAccount) {
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
        CreateClientRequest createClientRequest = (CreateClientRequest) o;
        return Objects.equals(this.client, createClientRequest.client) &&
                Objects.equals(this.card, createClientRequest.card) &&
                Objects.equals(this.cardAccount, createClientRequest.cardAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, card, cardAccount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateClientRequest {\n");
        sb.append("    client: ").append(toIndentedString(client)).append("\n");
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
