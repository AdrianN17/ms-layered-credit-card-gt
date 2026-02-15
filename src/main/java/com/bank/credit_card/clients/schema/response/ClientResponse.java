package com.bank.credit_card.clients.schema.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ClientResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-15T00:06:49.687636300-05:00[America/Lima]")
public class ClientResponse {

    private Long clientId;

    private String names;

    private String lastNames;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    private String phone;

    private String email;

    private String documentNumber;

    private String documentType;

    @Valid
    private List<@Valid CardResponse> cards = new ArrayList<>();

    public ClientResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ClientResponse(Long clientId, String names, String lastNames, LocalDate birthDate, String phone, String email, String documentNumber, String documentType, List<@Valid CardResponse> cards) {
        this.clientId = clientId;
        this.names = names;
        this.lastNames = lastNames;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.cards = cards;
    }

    public ClientResponse clientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     * Get clientId
     *
     * @return clientId
     */
    @NotNull
    @Schema(name = "clientId", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("clientId")
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public ClientResponse names(String names) {
        this.names = names;
        return this;
    }

    /**
     * Get names
     *
     * @return names
     */
    @NotNull
    @Schema(name = "names", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("names")
    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public ClientResponse lastNames(String lastNames) {
        this.lastNames = lastNames;
        return this;
    }

    /**
     * Get lastNames
     *
     * @return lastNames
     */
    @NotNull
    @Schema(name = "lastNames", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("lastNames")
    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public ClientResponse birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Get birthDate
     *
     * @return birthDate
     */
    @NotNull
    @Valid
    @Schema(name = "birthDate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("birthDate")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ClientResponse phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Get phone
     *
     * @return phone
     */
    @NotNull
    @Pattern(regexp = "^9\\d{8}$")
    @Schema(name = "phone", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ClientResponse email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Get email
     *
     * @return email
     */
    @NotNull
    @Email
    @Schema(name = "email", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientResponse documentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    /**
     * Get documentNumber
     *
     * @return documentNumber
     */
    @NotNull
    @Pattern(regexp = "^(?:\\d{8}|\\d{11}|[A-Za-z0-9]{6,12})$")
    @Schema(name = "documentNumber", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("documentNumber")
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public ClientResponse documentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    /**
     * Get documentType
     *
     * @return documentType
     */
    @NotNull
    @Pattern(regexp = "^(DNI|CE|RUC|PASSPORT)$")
    @Schema(name = "documentType", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("documentType")
    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }


    public ClientResponse cards(List<@Valid CardResponse> cards) {
        this.cards = cards;
        return this;
    }

    public ClientResponse addCardsItem(CardResponse cardsItem) {
        if (this.cards == null) {
            this.cards = new ArrayList<>();
        }
        this.cards.add(cardsItem);
        return this;
    }

    /**
     * Get cards
     *
     * @return cards
     */
    @NotNull
    @Valid
    @Schema(name = "cards", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("cards")
    public List<@Valid CardResponse> getCards() {
        return cards;
    }

    public void setCards(List<@Valid CardResponse> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientResponse clientResponse = (ClientResponse) o;
        return Objects.equals(this.clientId, clientResponse.clientId) &&
                Objects.equals(this.names, clientResponse.names) &&
                Objects.equals(this.lastNames, clientResponse.lastNames) &&
                Objects.equals(this.birthDate, clientResponse.birthDate) &&
                Objects.equals(this.phone, clientResponse.phone) &&
                Objects.equals(this.email, clientResponse.email) &&
                Objects.equals(this.documentNumber, clientResponse.documentNumber) &&
                Objects.equals(this.documentType, clientResponse.documentType) &&
                Objects.equals(this.cards, clientResponse.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, names, lastNames, birthDate, phone, email, documentNumber, documentType, cards);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClientResponse {\n");
        sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
        sb.append("    names: ").append(toIndentedString(names)).append("\n");
        sb.append("    lastNames: ").append(toIndentedString(lastNames)).append("\n");
        sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    documentNumber: ").append(toIndentedString(documentNumber)).append("\n");
        sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
        sb.append("    cards: ").append(toIndentedString(cards)).append("\n");
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

