package com.pismo.customers.infra.configuration.exception;

public class AccountNotFoundError extends RuntimeException {

    public AccountNotFoundError() {
        super("Account Not Found");
    }
}
