package com.pismo.customers.domain;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Account {

    private Long id;

    private String documentNumber;

    private Double balance;

    private Double creditLimit;

    public Account(Long id, String documentNumber, Double creditLimit) {
        this.id = id;
        this.documentNumber = requireNonNull(documentNumber);
        this.creditLimit = requireNonNull(creditLimit);
        this.balance = 0.0;
    }
    public Account(String documentNumber, Double creditLimit) {
        this.documentNumber = requireNonNull(documentNumber);
        this.creditLimit = requireNonNull(creditLimit);
        this.balance = 0.0;
    }

    public Double getBalance() {
        return this.balance;
    }

    public Double getCreditLimit() {
        return this.creditLimit;
    }

    public Long getId() {
        return this.id;
    }

    public String getDocumentNumber() {
        return this.documentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account accounts = (Account) o;
        return Objects.equals(id, accounts.id) && Objects.equals(documentNumber, accounts.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentNumber);
    }
}
