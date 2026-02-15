package com.bank.credit_card.clients.entity;

import com.bank.credit_card.clients.commons.DocumentType;
import com.bank.credit_card.generic.entity.GenericEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "Clients")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "names")
    private String names;

    @Column(name = "documentNumber")
    private String documentNumber;

    @Column(name = "documentType")
    private DocumentType documentType;

    @Column(name = "lastNames")
    private String lastNames;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<CardEntity> cardEntities;

}
