package com.pismo.customers.infra.adapters.repositories;

import com.pismo.customers.domain.Transaction;
import com.pismo.customers.domain.ports.repositories.TransactionRepositoryPort;
import com.pismo.customers.infra.adapters.entities.TransactionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepositoryPort {

    private final SpringTransactionRepository springTransactionRepository;

    @Override
    public Optional<TransactionEntity> save(Transaction transaction) {
        final TransactionEntity transactionEntity = new TransactionEntity(transaction);
        final TransactionEntity transactionEntityResponse = springTransactionRepository.save(transactionEntity);
        return Optional.of(transactionEntityResponse);
    }
}
