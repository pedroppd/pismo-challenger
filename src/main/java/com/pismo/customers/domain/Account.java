package com.pismo.customers.domain;

import java.util.Objects;

public class Account {

    private Long id;

    private String documentNumber;

    public Account(Long id, String documentNumber) {
        this.id = Objects.requireNonNull(id);
        this.documentNumber = Objects.requireNonNull(documentNumber);
    }

    public Account(String documentNumber) {
        this.documentNumber = Objects.requireNonNull(documentNumber);
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
