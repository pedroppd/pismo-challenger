package com.pismo.customers.infra.adapters.entities;

import com.pismo.customers.application.controllers.dto.response.AccountResponseDTO;
import com.pismo.customers.domain.Account;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_number", unique = true)
    private String documentNumber;

    public AccountEntity(Account account) {
        this.id = account.getId();
        this.documentNumber = account.getDocumentNumber();
    }

    public AccountResponseDTO toAccountResponseDTO() {
        return AccountResponseDTO.builder().id(this.getId()).documentNumber(this.getDocumentNumber()).build();
    }

    public Account toAccount() {
        return new Account(this.getId(), this.getDocumentNumber());
    }

}
