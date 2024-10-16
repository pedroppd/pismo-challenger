package com.pismo.customers.infra.adapters.entities;

import com.pismo.customers.application.controllers.dto.response.AccountResponseDTO;
import com.pismo.customers.domain.Account;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

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

    @Column(name = "balance")
    private Double balance;

    @Column(name = "credit_limit")
    private Double creditLimit;

    public AccountEntity(Account account) {
        this.id = account.getId();
        this.documentNumber = requireNonNull(account.getDocumentNumber());
        this.balance = account.getBalance();
        this.creditLimit = account.getCreditLimit();
    }

    public AccountResponseDTO toAccountResponseDTO() {
        return AccountResponseDTO.builder()
                .id(this.getId())
                .documentNumber(this.getDocumentNumber())
                .creditLimit(this.getCreditLimit())
                .build();
    }

    public Account toAccount() {
        return new Account(this.getId(), this.getDocumentNumber(), this.getCreditLimit());
    }

}
