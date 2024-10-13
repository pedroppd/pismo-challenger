package com.pismo.customers.infra.configuration.exception;

public class AccountNotFoundError extends RuntimeException {

    private Long id;

    public AccountNotFoundError(Long accountId) {
        super("Account Not Found");
        this.id = accountId;
    }

    public Long getId() {
        return this.id;
    }
}
