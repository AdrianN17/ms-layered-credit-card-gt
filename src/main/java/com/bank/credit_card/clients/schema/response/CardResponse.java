package com.bank.credit_card.clients.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

/**
 * CardResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-15T00:06:49.687636300-05:00[America/Lima]")
public class CardResponse {

    private Long cardId;

    private String typeCard;

    private String categoryCard;

    private String numberCard;

    private CardAccountResponse cardAccount;

    public CardResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public CardResponse(Long cardId, String typeCard, String categoryCard, String numberCard, CardAccountResponse cardAccount) {
        this.cardId = cardId;
        this.typeCard = typeCard;
        this.categoryCard = categoryCard;
        this.numberCard = numberCard;
        this.cardAccount = cardAccount;
    }

    public CardResponse cardId(Long cardId) {
        this.cardId = cardId;
        return this;
    }

    /**
     * Get cardId
     *
     * @return cardId
     */
    @NotNull
    @Schema(name = "cardId", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("cardId")
    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public CardResponse typeCard(String typeCard) {
        this.typeCard = typeCard;
        return this;
    }

    /**
     * Get typeCard
     *
     * @return typeCard
     */
    @NotNull
    @Pattern(regexp = "^(VISA|MASTERCARD)$")
    @Schema(name = "typeCard", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("typeCard")
    public String getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(String typeCard) {
        this.typeCard = typeCard;
    }

    public CardResponse categoryCard(String categoryCard) {
        this.categoryCard = categoryCard;
        return this;
    }

    /**
     * Get categoryCard
     *
     * @return categoryCard
     */
    @NotNull
    @Pattern(regexp = "^(NORMAL|SILVER|GOLD|PLATINIUM|BLACK|SIGNATURE|INFINITY)$")
    @Schema(name = "categoryCard", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("categoryCard")
    public String getCategoryCard() {
        return categoryCard;
    }

    public void setCategoryCard(String categoryCard) {
        this.categoryCard = categoryCard;
    }

    public CardResponse numberCard(String numberCard) {
        this.numberCard = numberCard;
        return this;
    }

    /**
     * Get numberCard
     *
     * @return numberCard
     */
    @NotNull
    @Schema(name = "numberCard", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("numberCard")
    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    /**
     * Get cardAccount
     *
     * @return cardAccount
     */
    @NotNull
    @Valid
    @Schema(name = "cardAccount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("cardAccount")
    public CardAccountResponse getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(CardAccountResponse cardAccount) {
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
        CardResponse cardResponse = (CardResponse) o;
        return Objects.equals(this.cardId, cardResponse.cardId) &&
                Objects.equals(this.typeCard, cardResponse.typeCard) &&
                Objects.equals(this.categoryCard, cardResponse.categoryCard) &&
                Objects.equals(this.numberCard, cardResponse.numberCard) &&
                Objects.equals(this.cardAccount, cardResponse.cardAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, typeCard, categoryCard, numberCard, cardAccount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CardResponse {\n");
        sb.append("    cardId: ").append(toIndentedString(cardId)).append("\n");
        sb.append("    typeCard: ").append(toIndentedString(typeCard)).append("\n");
        sb.append("    categoryCard: ").append(toIndentedString(categoryCard)).append("\n");
        sb.append("    numberCard: ").append(toIndentedString(numberCard)).append("\n");
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

