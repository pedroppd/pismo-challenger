package com.pismo.customers.domain.ports.repositories;

import com.pismo.customers.domain.Transaction;
import com.pismo.customers.infra.adapters.entities.TransactionEntity;

import java.util.Optional;

public interface TransactionRepositoryPort {

    Optional<TransactionEntity> save(Transaction account);
}
