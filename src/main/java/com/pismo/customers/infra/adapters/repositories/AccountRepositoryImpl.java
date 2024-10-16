package com.pismo.customers.infra.adapters.repositories;

import com.pismo.customers.domain.Account;
import com.pismo.customers.domain.ports.repositories.AccountRepositoryPort;
import com.pismo.customers.infra.adapters.entities.AccountEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryPort {

    private final SpringAccountRepository springAccountRepository;

    @Override
    public AccountEntity save(final Account account) {
        final AccountEntity accountEntity = new AccountEntity(account);
        return springAccountRepository.save(accountEntity);
    }

    @Override
    public Optional<AccountEntity> getById(final Long id) {
        return springAccountRepository.findById(id);
    }
}
