package com.bank.credit_card.balances.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BalanceResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-15T00:06:49.687636300-05:00[America/Lima]")
public class BalanceResponse {

    private Long cardId;

    private Long balanceId;

    private String currency;

    private BigDecimal consumptionAmount;

    private BigDecimal paymentAmount;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal exchangeRate;

    @Valid
    private List<@Valid BalanceDataResponse> balanceDataset = new ArrayList<>();

    public BalanceResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public BalanceResponse(Long cardId, Long balanceId, String currency, BigDecimal consumptionAmount, BigDecimal paymentAmount, LocalDate startDate, LocalDate endDate, BigDecimal exchangeRate, List<@Valid BalanceDataResponse> balanceDataset) {
        this.cardId = cardId;
        this.balanceId = balanceId;
        this.currency = currency;
        this.consumptionAmount = consumptionAmount;
        this.paymentAmount = paymentAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.exchangeRate = exchangeRate;
        this.balanceDataset = balanceDataset;
    }

    public BalanceResponse cardId(Long cardId) {
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

    public BalanceResponse balanceId(Long balanceId) {
        this.balanceId = balanceId;
        return this;
    }

    /**
     * Get balanceId
     *
     * @return balanceId
     */
    @Schema(name = "balanceId")
    @JsonProperty("balanceId")
    public Long getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Long balanceId) {
        this.balanceId = balanceId;
    }

    public BalanceResponse currency(String currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Get currency
     *
     * @return currency
     */
    @Schema(name = "currency")
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BalanceResponse consumptionAmount(BigDecimal consumptionAmount) {
        this.consumptionAmount = consumptionAmount;
        return this;
    }

    /**
     * Get consumptionAmount
     *
     * @return consumptionAmount
     */
    @NotNull
    @Valid
    @Schema(name = "consumptionAmount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("consumptionAmount")
    public BigDecimal getConsumptionAmount() {
        return consumptionAmount;
    }

    public void setConsumptionAmount(BigDecimal consumptionAmount) {
        this.consumptionAmount = consumptionAmount;
    }

    public BalanceResponse paymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    /**
     * Get paymentAmount
     *
     * @return paymentAmount
     */
    @NotNull
    @Valid
    @Schema(name = "paymentAmount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("paymentAmount")
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BalanceResponse startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Get startDate
     *
     * @return startDate
     */
    @NotNull
    @Valid
    @Schema(name = "startDate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("startDate")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public BalanceResponse endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Get endDate
     *
     * @return endDate
     */
    @NotNull
    @Valid
    @Schema(name = "endDate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("endDate")
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BalanceResponse exchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
        return this;
    }

    /**
     * Get exchangeRate
     *
     * @return exchangeRate
     */
    @NotNull
    @Valid
    @Schema(name = "exchangeRate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("exchangeRate")
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BalanceResponse balanceDataset(List<@Valid BalanceDataResponse> balanceDataset) {
        this.balanceDataset = balanceDataset;
        return this;
    }

    public BalanceResponse addBalanceDatasetItem(BalanceDataResponse balanceDatasetItem) {
        if (this.balanceDataset == null) {
            this.balanceDataset = new ArrayList<>();
        }
        this.balanceDataset.add(balanceDatasetItem);
        return this;
    }

    /**
     * Get balanceDataset
     *
     * @return balanceDataset
     */
    @NotNull
    @Valid
    @Schema(name = "balanceDataset", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("balanceDataset")
    public List<@Valid BalanceDataResponse> getBalanceDataset() {
        return balanceDataset;
    }

    public void setBalanceDataset(List<@Valid BalanceDataResponse> balanceDataset) {
        this.balanceDataset = balanceDataset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BalanceResponse balanceResponse = (BalanceResponse) o;
        return Objects.equals(this.cardId, balanceResponse.cardId) &&
                Objects.equals(this.balanceId, balanceResponse.balanceId) &&
                Objects.equals(this.currency, balanceResponse.currency) &&
                Objects.equals(this.consumptionAmount, balanceResponse.consumptionAmount) &&
                Objects.equals(this.paymentAmount, balanceResponse.paymentAmount) &&
                Objects.equals(this.startDate, balanceResponse.startDate) &&
                Objects.equals(this.endDate, balanceResponse.endDate) &&
                Objects.equals(this.exchangeRate, balanceResponse.exchangeRate) &&
                Objects.equals(this.balanceDataset, balanceResponse.balanceDataset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, balanceId, currency, consumptionAmount, paymentAmount, startDate, endDate, exchangeRate, balanceDataset);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BalanceResponse {\n");
        sb.append("    cardId: ").append(toIndentedString(cardId)).append("\n");
        sb.append("    balanceId: ").append(toIndentedString(balanceId)).append("\n");
        sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
        sb.append("    consumptionAmount: ").append(toIndentedString(consumptionAmount)).append("\n");
        sb.append("    paymentAmount: ").append(toIndentedString(paymentAmount)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    exchangeRate: ").append(toIndentedString(exchangeRate)).append("\n");
        sb.append("    balanceDataset: ").append(toIndentedString(balanceDataset)).append("\n");
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
