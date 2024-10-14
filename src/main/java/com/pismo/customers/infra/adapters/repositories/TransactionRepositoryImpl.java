package com.pismo.customers.infra.adapters.repositories;

import com.pismo.customers.domain.Transaction;
import com.pismo.customers.domain.ports.repositories.TransactionRepositoryPort;
import com.pismo.customers.infra.adapters.entities.TransactionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepositoryPort {

    private final SpringTransactionRepository springTransactionRepository;

    @Override
    public TransactionEntity save(final Transaction transaction) {
        final TransactionEntity transactionEntity = new TransactionEntity(transaction);
        return springTransactionRepository.save(transactionEntity);
    }
}
