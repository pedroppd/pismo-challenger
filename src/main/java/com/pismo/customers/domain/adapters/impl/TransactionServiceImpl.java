package com.pismo.customers.domain.adapters.impl;

import com.pismo.customers.application.controllers.dto.request.TransactionRequestDTO;
import com.pismo.customers.application.controllers.dto.response.TransactionResponseDTO;
import com.pismo.customers.domain.Account;
import com.pismo.customers.domain.Transaction;
import com.pismo.customers.domain.enums.OperationTypeEnum;
import com.pismo.customers.domain.ports.repositories.AccountRepositoryPort;
import com.pismo.customers.domain.ports.repositories.TransactionRepositoryPort;
import com.pismo.customers.infra.adapters.entities.TransactionEntity;
import com.pismo.customers.infra.configuration.exception.AccountNotFoundError;
import org.springframework.data.util.Pair;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.EnumSet;

public class TransactionServiceImpl {

    private final TransactionRepositoryPort transactionRepository;
    private final AccountRepositoryPort accountRepositoryPort;

    public TransactionServiceImpl(final TransactionRepositoryPort transactionRepository,
                                  final AccountRepositoryPort accountRepositoryPort) {
        this.transactionRepository = transactionRepository;
        this.accountRepositoryPort = accountRepositoryPort;
    }

    public TransactionResponseDTO save(final TransactionRequestDTO transactionRequest) throws AccountNotFoundException {
        final var pairResponse = getAccountAndOperationId(transactionRequest);
        final var amount = getAmountByOperationType(transactionRequest.getAmount(), pairResponse.getSecond());
        final Transaction transaction = new Transaction(pairResponse.getFirst(), pairResponse.getSecond(), amount);
        final TransactionEntity transactionEntity = this.transactionRepository.save(transaction);
        return transactionEntity.toTransactionResponseDTO();
    }

    private Double getAmountByOperationType(final Double amount, final OperationTypeEnum operationType) {
        final EnumSet<OperationTypeEnum> debtOperations = EnumSet.of(
                OperationTypeEnum.PURCHASE,
                OperationTypeEnum.INSTALLMENT_PURCHASE,
                OperationTypeEnum.WITHDRAWAL);
        final var value = BigDecimal.valueOf(amount).abs();
        if (debtOperations.contains(operationType)) {
            return value.negate().doubleValue();
        }
        return amount;
    }

    private Pair<Account, OperationTypeEnum> getAccountAndOperationId(final TransactionRequestDTO transactionRequest) throws AccountNotFoundException {
        final var optionalAccount = accountRepositoryPort.getById(transactionRequest.getAccountId());
        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundError(transactionRequest.getAccountId());
        }
        final OperationTypeEnum operationType = OperationTypeEnum.getByKey(transactionRequest.getOperationTypeId());
        return Pair.of(optionalAccount.get().toAccount(), operationType);
    }
}
