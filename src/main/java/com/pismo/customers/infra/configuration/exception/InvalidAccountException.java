package com.pismo.customers.infra.configuration.exception;

public class InvalidAccountException extends RuntimeException {

    public InvalidAccountException() {
        super("The account entered is invalid");
    }
}
