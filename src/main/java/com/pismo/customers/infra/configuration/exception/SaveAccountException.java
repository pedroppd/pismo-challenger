package com.pismo.customers.infra.configuration.exception;

public class SaveAccountException extends RuntimeException {

    public SaveAccountException() {
        super("Error when trying to save account");
    }
}
