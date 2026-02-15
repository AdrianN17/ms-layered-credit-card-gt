package com.bank.credit_card.balances.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * BalanceDataResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-15T00:06:49.687636300-05:00[America/Lima]")
public class BalanceDataResponse {

    private String originName;

    private BigDecimal amount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime date;

    private String status;

    private String typeTransaccion;

    private String currency;

    private String origin;

    private String category;

    public BalanceDataResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public BalanceDataResponse(String originName, BigDecimal amount, OffsetDateTime date, String status, String typeTransaccion, String currency) {
        this.originName = originName;
        this.amount = amount;
        this.date = date;
        this.status = status;
        this.typeTransaccion = typeTransaccion;
        this.currency = currency;
    }

    public BalanceDataResponse originName(String originName) {
        this.originName = originName;
        return this;
    }

    /**
     * Get originName
     *
     * @return originName
     */
    @NotNull
    @Schema(name = "originName", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("originName")
    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public BalanceDataResponse amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Get amount
     *
     * @return amount
     */
    @NotNull
    @Valid
    @Schema(name = "amount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BalanceDataResponse date(OffsetDateTime date) {
        this.date = date;
        return this;
    }

    /**
     * Get date
     *
     * @return date
     */
    @NotNull
    @Valid
    @Schema(name = "date", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("date")
    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public BalanceDataResponse status(String status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     */
    @NotNull
    @Schema(name = "status", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BalanceDataResponse typeTransaccion(String typeTransaccion) {
        this.typeTransaccion = typeTransaccion;
        return this;
    }

    /**
     * Get typeTransaccion
     *
     * @return typeTransaccion
     */
    @NotNull
    @Schema(name = "typeTransaccion", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("typeTransaccion")
    public String getTypeTransaccion() {
        return typeTransaccion;
    }

    public void setTypeTransaccion(String typeTransaccion) {
        this.typeTransaccion = typeTransaccion;
    }

    public BalanceDataResponse currency(String currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Get currency
     *
     * @return currency
     */
    @NotNull
    @Schema(name = "currency", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BalanceDataResponse origin(String origin) {
        this.origin = origin;
        return this;
    }

    /**
     * Get origin
     *
     * @return origin
     */

    @Schema(name = "origin", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("origin")
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public BalanceDataResponse category(String category) {
        this.category = category;
        return this;
    }

    /**
     * Get category
     *
     * @return category
     */

    @Schema(name = "category", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BalanceDataResponse balanceDataResponse = (BalanceDataResponse) o;
        return Objects.equals(this.originName, balanceDataResponse.originName) &&
                Objects.equals(this.amount, balanceDataResponse.amount) &&
                Objects.equals(this.date, balanceDataResponse.date) &&
                Objects.equals(this.status, balanceDataResponse.status) &&
                Objects.equals(this.typeTransaccion, balanceDataResponse.typeTransaccion) &&
                Objects.equals(this.currency, balanceDataResponse.currency) &&
                Objects.equals(this.origin, balanceDataResponse.origin) &&
                Objects.equals(this.category, balanceDataResponse.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originName, amount, date, status, typeTransaccion, currency, origin, category);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BalanceDataResponse {\n");
        sb.append("    originName: ").append(toIndentedString(originName)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    date: ").append(toIndentedString(date)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    typeTransaccion: ").append(toIndentedString(typeTransaccion)).append("\n");
        sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
        sb.append("    origin: ").append(toIndentedString(origin)).append("\n");
        sb.append("    category: ").append(toIndentedString(category)).append("\n");
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

