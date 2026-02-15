package com.bank.credit_card.clients.schema.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

/**
 * CardRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-15T00:06:49.687636300-05:00[America/Lima]")
public class CardRequest {

    private String typeCard;

    private String categoryCard;

    public CardRequest() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public CardRequest(String typeCard, String categoryCard) {
        this.typeCard = typeCard;
        this.categoryCard = categoryCard;
    }

    public CardRequest typeCard(String typeCard) {
        this.typeCard = typeCard;
        return this;
    }

    /**
     * Get typeCard
     *
     * @return typeCard
     */
    @NotNull(message = "typeCard is required")
    @NotBlank(message = "typeCard must not be blank")
    @Pattern(regexp = "^(VISA|MASTERCARD)$", message = "typeCard must be either VISA or MASTERCARD")
    @Schema(name = "typeCard", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("typeCard")
    public String getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(String typeCard) {
        this.typeCard = typeCard;
    }

    public CardRequest categoryCard(String categoryCard) {
        this.categoryCard = categoryCard;
        return this;
    }

    /**
     * Get categoryCard
     *
     * @return categoryCard
     */
    @NotNull(message = "categoryCard is required")
    @NotBlank(message = "categoryCard must not be blank")
    @Pattern(regexp = "^(NORMAL|SILVER|GOLD|PLATINUM|BLACK|SIGNATURE|INFINITY)$", message = "categoryCard must be one of NORMAL, SILVER, GOLD, PLATINUM, BLACK, SIGNATURE, INFINITY")
    @Schema(name = "categoryCard", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("categoryCard")
    public String getCategoryCard() {
        return categoryCard;
    }

    public void setCategoryCard(String categoryCard) {
        this.categoryCard = categoryCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardRequest cardRequest = (CardRequest) o;
        return Objects.equals(this.typeCard, cardRequest.typeCard) &&
                Objects.equals(this.categoryCard, cardRequest.categoryCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeCard, categoryCard);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CardRequest {\n");
        sb.append("    typeCard: ").append(toIndentedString(typeCard)).append("\n");
        sb.append("    categoryCard: ").append(toIndentedString(categoryCard)).append("\n");
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
