package com.bank.credit_card.generic.entity;

import com.bank.credit_card.generic.converter.StatusEnumConverter;
import com.bank.credit_card.generic.enums.StatusEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class GenericEntity {
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @Convert(converter = StatusEnumConverter.class)
    @Column(name = "status")
    private StatusEnum status;

    public void softDelete() {
        this.setStatus(StatusEnum.INACTIVE);
        this.setUpdatedDate(LocalDateTime.now());
    }

    @PrePersist
    void persit() {
        this.setStatus(StatusEnum.ACTIVE);
        this.setCreatedDate(LocalDateTime.now());
    }

    @PreUpdate
    void onUpdate() {
        this.setUpdatedDate(LocalDateTime.now());
    }
}
