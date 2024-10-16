package com.pismo.customers.domain;

import com.pismo.customers.domain.enums.OperationTypeEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Transaction {

    private Long id;

    private Account account;

    private OperationTypeEnum operationType;

    private Double amount;

    private String eventDate;

    public Transaction(final Account account,
                       final OperationTypeEnum operationType,
                       final Double amount) {
        this.account = Objects.requireNonNull(account);
        this.operationType = Objects.requireNonNull(operationType);
        this.amount = Objects.requireNonNull(amount);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.n");
        this.eventDate = LocalDateTime.now().format(formatter);
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public OperationTypeEnum getOperationType() {
        return operationType;
    }

    public Double getAmount() {
        return amount;
    }

    public String getEventDate() {
        return eventDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(account, that.account) && operationType == that.operationType && Objects.equals(amount, that.amount) && Objects.equals(eventDate, that.eventDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, operationType, amount, eventDate);
    }
}
