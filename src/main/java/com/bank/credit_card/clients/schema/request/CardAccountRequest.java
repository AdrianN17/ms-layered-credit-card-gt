package com.bank.credit_card.clients.schema.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * CardAccountRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-15T00:06:49.687636300-05:00[America/Lima]")
public class CardAccountRequest {

    private BigDecimal crediticialTotalAmount;

    private BigDecimal debtTax;

    private Integer facturationDate;

    private Integer paymentDate;

    private String currency;

    public CardAccountRequest() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public CardAccountRequest(BigDecimal crediticialTotalAmount, BigDecimal debtTax, Integer facturationDate, Integer paymentdate, String currency) {
        this.crediticialTotalAmount = crediticialTotalAmount;
        this.debtTax = debtTax;
        this.facturationDate = facturationDate;
        this.paymentDate = paymentdate;
        this.currency = currency;
    }

    public CardAccountRequest crediticialTotalAmount(BigDecimal crediticialTotalAmount) {
        this.crediticialTotalAmount = crediticialTotalAmount;
        return this;
    }

    /**
     * Get crediticialTotalAmount
     *
     * @return crediticialTotalAmount
     */
    @NotNull(message = "crediticialTotalAmount is required")
    @Valid
    @Schema(name = "crediticialTotalAmount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("crediticialTotalAmount")
    public BigDecimal getCrediticialTotalAmount() {
        return crediticialTotalAmount;
    }

    public void setCrediticialTotalAmount(BigDecimal crediticialTotalAmount) {
        this.crediticialTotalAmount = crediticialTotalAmount;
    }

    public CardAccountRequest debtTax(BigDecimal debtTax) {
        this.debtTax = debtTax;
        return this;
    }

    /**
     * Get debtTax
     *
     * @return debtTax
     */
    @NotNull(message = "debtTax is required")
    @Valid
    @Schema(name = "debtTax", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("debtTax")
    public BigDecimal getDebtTax() {
        return debtTax;
    }

    public void setDebtTax(BigDecimal debtTax) {
        this.debtTax = debtTax;
    }

    public CardAccountRequest facturationDate(Integer facturationDate) {
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
    @NotNull(message = "facturationDate is required")
    @Min(value = 1, message = "facturationDate must be >= 1")
    @Max(value = 31, message = "facturationDate must be <= 31")
    @Schema(name = "facturationDate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("facturationDate")
    public Integer getFacturationDate() {
        return facturationDate;
    }

    public void setFacturationDate(Integer facturationDate) {
        this.facturationDate = facturationDate;
    }

    public CardAccountRequest paymentdate(Integer paymentdate) {
        this.paymentDate = paymentdate;
        return this;
    }

    /**
     * Get paymentdate
     * minimum: 1
     * maximum: 31
     *
     * @return paymentdate
     */
    @NotNull(message = "paymentDate is required")
    @Min(value = 1, message = "paymentDate must be >= 1")
    @Max(value = 31, message = "paymentDate must be <= 31")
    @Schema(name = "paymentdate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("paymentdate")
    public Integer getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Integer paymentDate) {
        this.paymentDate = paymentDate;
    }

    public CardAccountRequest currency(String currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Get currency
     *
     * @return currency
     */
    @NotNull(message = "currency is required")
    @NotBlank(message = "currency must not be blank")
    @Pattern(regexp = "^(PEN|USD)$", message = "currency must be either PEN or USD")
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
        CardAccountRequest cardAccountRequest = (CardAccountRequest) o;
        return Objects.equals(this.crediticialTotalAmount, cardAccountRequest.crediticialTotalAmount) &&
                Objects.equals(this.debtTax, cardAccountRequest.debtTax) &&
                Objects.equals(this.facturationDate, cardAccountRequest.facturationDate) &&
                Objects.equals(this.paymentDate, cardAccountRequest.paymentDate) &&
                Objects.equals(this.currency, cardAccountRequest.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crediticialTotalAmount, debtTax, facturationDate, paymentDate, currency);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CardAccountRequest {\n");
        sb.append("    crediticialTotalAmount: ").append(toIndentedString(crediticialTotalAmount)).append("\n");
        sb.append("    debtTax: ").append(toIndentedString(debtTax)).append("\n");
        sb.append("    facturationDate: ").append(toIndentedString(facturationDate)).append("\n");
        sb.append("    paymentdate: ").append(toIndentedString(paymentDate)).append("\n");
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
