package com.pismo.customers.infra.configuration.exception;

public class SaveTransactionException extends RuntimeException {

    public SaveTransactionException() {
        super("Error when trying to save transaction");
    }
}
