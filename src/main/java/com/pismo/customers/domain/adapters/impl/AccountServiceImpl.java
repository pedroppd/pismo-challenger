package com.pismo.customers.domain.adapters.impl;


import com.pismo.customers.application.controllers.dto.request.AccountRequestDTO;
import com.pismo.customers.application.controllers.dto.response.AccountResponseDTO;
import com.pismo.customers.domain.Account;
import com.pismo.customers.domain.ports.repositories.AccountRepositoryPort;
import com.pismo.customers.infra.adapters.entities.AccountEntity;
import com.pismo.customers.infra.configuration.exception.AccountNotFoundError;
import com.pismo.customers.infra.configuration.exception.InvalidAccountException;

import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

public class AccountServiceImpl {

    private final AccountRepositoryPort accountRepository;

    public AccountServiceImpl(final AccountRepositoryPort accountRepository) {
        this.accountRepository = accountRepository;
    }

    //Adding new comment
    public AccountResponseDTO save(final AccountRequestDTO accountRequestDTO) {
        final Account account = new Account(accountRequestDTO.getDocumentNumber(), accountRequestDTO.getCreditLimit());
        final AccountEntity accountEntity = this.accountRepository.save(account);
        return accountEntity.toAccountResponseDTO();
    }

    public AccountResponseDTO getById(final Long id) {
        if (isEmpty(id)) {
            throw new InvalidAccountException();
        }
        final Optional<AccountEntity> accountEntity = this.accountRepository.getById(id);
        if (accountEntity.isEmpty()) {
            throw new AccountNotFoundError(id);
        }
        return accountEntity.get().toAccountResponseDTO();
    }
}
