package com.bank.credit_card.clients.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * CardAccountResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-15T00:06:49.687636300-05:00[America/Lima]")
public class CardAccountResponse {

    private Long cardAccountId;

    private BigDecimal crediticialTotalAmount;

    private BigDecimal debtTax;

    private Short facturationDate;

    private Short paymentDate;

    private String currency;

    public CardAccountResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public CardAccountResponse(Long cardAccountId, BigDecimal crediticialTotalAmount, BigDecimal debtTax, Short facturationDate, Short paymentDate, String currency) {
        this.cardAccountId = cardAccountId;
        this.crediticialTotalAmount = crediticialTotalAmount;
        this.debtTax = debtTax;
        this.facturationDate = facturationDate;
        this.paymentDate = paymentDate;
        this.currency = currency;
    }

    public CardAccountResponse cardAccountId(Long cardAccountId) {
        this.cardAccountId = cardAccountId;
        return this;
    }

    /**
     * Get cardAccountId
     *
     * @return cardAccountId
     */
    @NotNull
    @Schema(name = "cardAccountId", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("cardAccountId")
    public Long getCardAccountId() {
        return cardAccountId;
    }

    public void setCardAccountId(Long cardAccountId) {
        this.cardAccountId = cardAccountId;
    }

    public CardAccountResponse crediticialTotalAmount(BigDecimal crediticialTotalAmount) {
        this.crediticialTotalAmount = crediticialTotalAmount;
        return this;
    }

    /**
     * Get crediticialTotalAmount
     *
     * @return crediticialTotalAmount
     */
    @NotNull
    @Valid
    @Schema(name = "crediticialTotalAmount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("crediticialTotalAmount")
    public BigDecimal getCrediticialTotalAmount() {
        return crediticialTotalAmount;
    }

    public void setCrediticialTotalAmount(BigDecimal crediticialTotalAmount) {
        this.crediticialTotalAmount = crediticialTotalAmount;
    }

    public CardAccountResponse debtTax(BigDecimal debtTax) {
        this.debtTax = debtTax;
        return this;
    }

    /**
     * Get debtTax
     *
     * @return debtTax
     */
    @NotNull
    @Valid
    @Schema(name = "debtTax", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("debtTax")
    public BigDecimal getDebtTax() {
        return debtTax;
    }

    public void setDebtTax(BigDecimal debtTax) {
        this.debtTax = debtTax;
    }

    public CardAccountResponse facturationDate(Short facturationDate) {
        this.facturationDate = facturationDate;
        return this;
    }

    /**
     * Get facturationDate
     * minimum: 1
     * maximum: 31
     *
     * @return facturationDate
     */
    @NotNull
    @Min(1)
    @Max(31)
    @Schema(name = "facturationDate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("facturationDate")
    public Short getFacturationDate() {
        return facturationDate;
    }

    public void setFacturationDate(Short facturationDate) {
        this.facturationDate = facturationDate;
    }

    public CardAccountResponse paymentdate(Short paymentdate) {
        this.paymentDate = paymentdate;
        return this;
    }

    /**
     * Get paymentDate
     * minimum: 1
     * maximum: 31
     *
     * @return paymentDate
     */
    @NotNull
    @Min(1)
    @Max(31)
    @Schema(name = "paymentDate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("paymentDate")
    public Short getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Short paymentDate) {
        this.paymentDate = paymentDate;
    }

    public CardAccountResponse currency(String currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Get currency
     *
     * @return currency
     */
    @NotNull
    @Pattern(regexp = "^(PEN|USD)$")
    @Schema(name = "currency", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardAccountResponse cardAccountResponse = (CardAccountResponse) o;
        return Objects.equals(this.cardAccountId, cardAccountResponse.cardAccountId) &&
                Objects.equals(this.crediticialTotalAmount, cardAccountResponse.crediticialTotalAmount) &&
                Objects.equals(this.debtTax, cardAccountResponse.debtTax) &&
                Objects.equals(this.facturationDate, cardAccountResponse.facturationDate) &&
                Objects.equals(this.paymentDate, cardAccountResponse.paymentDate) &&
                Objects.equals(this.currency, cardAccountResponse.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardAccountId, crediticialTotalAmount, debtTax, facturationDate, paymentDate, currency);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CardAccountResponse {\n");
        sb.append("    cardAccountId: ").append(toIndentedString(cardAccountId)).append("\n");
        sb.append("    crediticialTotalAmount: ").append(toIndentedString(crediticialTotalAmount)).append("\n");
        sb.append("    debtTax: ").append(toIndentedString(debtTax)).append("\n");
        sb.append("    facturationDate: ").append(toIndentedString(facturationDate)).append("\n");
        sb.append("    paymentDate: ").append(toIndentedString(paymentDate)).append("\n");
        sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
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

