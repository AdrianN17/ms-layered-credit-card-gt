package com.bank.credit_card.generic.entity;

import com.bank.credit_card.generic.commons.Status;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class GenericEntity {
    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @Column(name = "status")
    private Status status;

    public void softDelete() {
        this.setStatus(Status.INACTIVE);
        this.setUpdatedDate(LocalDateTime.now());
    }

    @PrePersist
    void persit() {
        this.setStatus(Status.ACTIVE);
        this.setCreatedDate(LocalDateTime.now());
    }
}
