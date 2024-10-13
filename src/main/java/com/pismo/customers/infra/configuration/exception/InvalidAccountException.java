package com.pismo.customers.infra.configuration.exception;

public class InvalidAccountException extends RuntimeException {

    public InvalidAccountException() {
        super("Invalid Account");
    }
}
