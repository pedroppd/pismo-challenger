package com.pismo.customers.infra.configuration;

import com.pismo.customers.domain.adapters.impl.AccountServiceImpl;
import com.pismo.customers.domain.adapters.impl.TransactionServiceImpl;
import com.pismo.customers.infra.adapters.repositories.AccountRepositoryImpl;
import com.pismo.customers.infra.adapters.repositories.TransactionRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AccountServiceImpl accountServiceImpl(final AccountRepositoryImpl accountRepositoryImpl) {
        return new AccountServiceImpl(accountRepositoryImpl);
    }

    @Bean
    public TransactionServiceImpl transactionServiceImpl(final TransactionRepositoryImpl transactionRepositoryImpl,
                                                         final AccountRepositoryImpl accountRepositoryImpl) {
        return new TransactionServiceImpl(transactionRepositoryImpl, accountRepositoryImpl);
    }
}
