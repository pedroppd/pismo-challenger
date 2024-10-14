package com.pismo.customers.domain.ports.repositories;

import com.pismo.customers.domain.Account;
import com.pismo.customers.infra.adapters.entities.AccountEntity;

import java.util.Optional;

public interface AccountRepositoryPort {

    AccountEntity save(final Account account);
    Optional<AccountEntity> getById(final Long id);
}
