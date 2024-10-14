package com.pismo.customers.domain.ports.repositories;

import com.pismo.customers.domain.Transaction;
import com.pismo.customers.infra.adapters.entities.TransactionEntity;

public interface TransactionRepositoryPort {

    TransactionEntity save(final Transaction account);
}
