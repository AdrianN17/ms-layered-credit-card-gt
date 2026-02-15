package com.bank.credit_card.consumptions.schema.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * SplitConsumptionDebtRequest
 */

@JsonTypeName("splitConsumptionDebt_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-15T00:06:49.687636300-05:00[America/Lima]")
public class SplitConsumptionDebtRequest {

    private Integer quantity;

    public SplitConsumptionDebtRequest() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public SplitConsumptionDebtRequest(Integer quantity) {
        this.quantity = quantity;
    }

    public SplitConsumptionDebtRequest quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * Get quantity
     * minimum: 1
     * maximum: 36
     *
     * @return quantity
     */
    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "quantity must be >= 1")
    @Max(value = 36, message = "quantity must be <= 36")
    @Schema(name = "quantity", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SplitConsumptionDebtRequest splitConsumptionDebtRequest = (SplitConsumptionDebtRequest) o;
        return Objects.equals(this.quantity, splitConsumptionDebtRequest.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SplitConsumptionDebtRequest {\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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
