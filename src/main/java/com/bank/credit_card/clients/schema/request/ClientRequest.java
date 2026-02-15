package com.bank.credit_card.clients.schema.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

/**
 * ClientRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-15T00:06:49.687636300-05:00[America/Lima]")
public class ClientRequest {

    private String names;

    private String lastNames;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    private String phone;

    private String email;

    private String documentNumber;

    private String documentType;

    public ClientRequest() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ClientRequest(String names, String lastNames, LocalDate birthDate, String phone, String email, String documentNumber, String documentType) {
        this.names = names;
        this.lastNames = lastNames;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
    }

    public ClientRequest names(String names) {
        this.names = names;
        return this;
    }

    /**
     * Get names
     *
     * @return names
     */
    @NotNull(message = "names is required")
    @NotBlank(message = "names must not be blank")
    @Schema(name = "names", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("names")
    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public ClientRequest lastNames(String lastNames) {
        this.lastNames = lastNames;
        return this;
    }

    /**
     * Get lastNames
     *
     * @return lastNames
     */
    @NotNull(message = "lastNames is required")
    @NotBlank(message = "lastNames must not be blank")
    @Schema(name = "lastNames", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("lastNames")
    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public ClientRequest birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Get birthDate
     *
     * @return birthDate
     */
    @NotNull(message = "birthDate is required")
    @Valid
    @Schema(name = "birthDate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("birthDate")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ClientRequest phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Get phone
     *
     * @return phone
     */
    @NotNull(message = "phone is required")
    @NotBlank(message = "phone must not be blank")
    @Pattern(regexp = "^9\\d{8}$", message = "phone must match pattern ^9\\d{8}$")
    @Schema(name = "phone", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ClientRequest email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Get email
     *
     * @return email
     */
    @NotNull(message = "email is required")
    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be a valid email address")
    @Schema(name = "email", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientRequest documentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    /**
     * Get documentNumber
     *
     * @return documentNumber
     */
    @NotNull(message = "documentNumber is required")
    @NotBlank(message = "documentNumber must not be blank")
    @Pattern(regexp = "^(?:\\d{8}|\\d{11}|[A-Za-z0-9]{6,12})$", message = "documentNumber must match the required pattern")
    @Schema(name = "documentNumber", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("documentNumber")
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public ClientRequest documentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    /**
     * Get documentType
     *
     * @return documentType
     */
    @NotNull(message = "documentType is required")
    @NotBlank(message = "documentType must not be blank")
    @Pattern(regexp = "^(DNI|CE|RUC|PASSPORT)$", message = "documentType must be one of DNI, CE, RUC, PASSPORT")
    @Schema(name = "documentType", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("documentType")
    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientRequest clientRequest = (ClientRequest) o;
        return Objects.equals(this.names, clientRequest.names) &&
                Objects.equals(this.lastNames, clientRequest.lastNames) &&
                Objects.equals(this.birthDate, clientRequest.birthDate) &&
                Objects.equals(this.phone, clientRequest.phone) &&
                Objects.equals(this.email, clientRequest.email) &&
                Objects.equals(this.documentNumber, clientRequest.documentNumber) &&
                Objects.equals(this.documentType, clientRequest.documentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names, lastNames, birthDate, phone, email, documentNumber, documentType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClientRequest {\n");
        sb.append("    names: ").append(toIndentedString(names)).append("\n");
        sb.append("    lastNames: ").append(toIndentedString(lastNames)).append("\n");
        sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    documentNumber: ").append(toIndentedString(documentNumber)).append("\n");
        sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
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
