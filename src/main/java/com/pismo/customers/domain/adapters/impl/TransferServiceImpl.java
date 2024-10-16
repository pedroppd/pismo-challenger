package com.pismo.customers.domain.adapters.impl;


import com.pismo.customers.application.controllers.dto.request.AccountRequestDTO;
import com.pismo.customers.application.controllers.dto.request.TransferRequestDTO;
import com.pismo.customers.application.controllers.dto.response.AccountResponseDTO;
import com.pismo.customers.application.controllers.dto.response.TransferResponseDTO;
import com.pismo.customers.domain.Account;
import com.pismo.customers.domain.Transaction;
import com.pismo.customers.domain.enums.OperationTypeEnum;
import com.pismo.customers.domain.ports.repositories.AccountRepositoryPort;
import com.pismo.customers.domain.ports.repositories.TransactionRepositoryPort;
import com.pismo.customers.domain.ports.repositories.TransferRepositoryPort;
import com.pismo.customers.infra.adapters.entities.AccountEntity;
import com.pismo.customers.infra.configuration.exception.AccountNotFoundError;
import com.pismo.customers.infra.configuration.exception.InvalidAccountException;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

public class TransferServiceImpl {

    private final AccountRepositoryPort accountRepositoryPort;
    private final TransactionRepositoryPort transactionRepositoryPort;


    public TransferServiceImpl(final AccountRepositoryPort accountRepositoryPort,
                               final TransactionRepositoryPort transactionRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Transactional
    public TransferResponseDTO save(final TransferRequestDTO transferRequestDTO) {
        final Optional<AccountEntity> fromAccount = accountRepositoryPort.getById(transferRequestDTO.getFromIdAccount());
        final Double balance = getBalance(transferRequestDTO.getTransferValue());

        if (fromAccount.isEmpty()) {
            throw new AccountNotFoundError(transferRequestDTO.getFromIdAccount());
        }

        final Optional<AccountEntity> toAccount = accountRepositoryPort.getById(transferRequestDTO.getToIdAccount());
        if (toAccount.isEmpty()) {
            throw new AccountNotFoundError(transferRequestDTO.getFromIdAccount());
        }

        final Transaction fromTransaction = new Transaction(fromAccount.get().toAccount(), OperationTypeEnum.WITHDRAWAL, balance);
        final var fromTransactionResponse = transactionRepositoryPort.save(fromTransaction);

        final Transaction toTransaction = new Transaction(fromAccount.get().toAccount(), OperationTypeEnum.TRANSFER, BigDecimal.valueOf(balance).abs().doubleValue());
        final var toTransactionResponse = transactionRepositoryPort.save(toTransaction);

        return TransferResponseDTO.builder()
                .transactionFrom(fromTransactionResponse.toTransactionResponseDTO())
                .transactionTo(toTransactionResponse.toTransactionResponseDTO())
                .balance(balance)
                .build();
    }

    private Double setBalance(AccountEntity accountEntity, Double withdraw) {
        final var creditLimit = BigDecimal.valueOf(accountEntity.getCreditLimit());
        final var balance = BigDecimal.valueOf(accountEntity.getBalance());
        final var valueWithdraw = BigDecimal.valueOf(withdraw);

        if(valueWithdraw.compareTo(creditLimit) <= 0
                && valueWithdraw.compareTo(balance) <= 0) {
            return withdraw;
        }
        throw new IllegalArgumentException("");
    }

    private Double getBalance(Double balance) {
        return balance.compareTo(0.0) < 0 ? balance : BigDecimal.valueOf(balance).negate().doubleValue();
    }
}
