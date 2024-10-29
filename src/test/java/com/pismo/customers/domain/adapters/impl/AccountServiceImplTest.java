package com.pismo.customers.domain.adapters.impl;

import com.pismo.customers.application.controllers.dto.request.AccountRequestDTO;
import com.pismo.customers.application.controllers.dto.response.AccountResponseDTO;
import com.pismo.customers.infra.adapters.entities.AccountEntity;
import com.pismo.customers.infra.adapters.repositories.AccountRepositoryImpl;
import com.pismo.customers.infra.configuration.exception.AccountNotFoundError;
import com.pismo.customers.infra.configuration.exception.InvalidAccountException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceImplTest {

    @Mock
    private AccountRepositoryImpl accountRepositoryImpl;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;


    @Test
    public void saveAccountSuccess() {
        final var accountRequestMock = createAccountEntityMock();
        when(accountRepositoryImpl.save(any())).thenReturn(accountRequestMock);
        final var accountResponse = accountServiceImpl.save(createAccountRequestDTOMock());
        assertNotNull(accountResponse);
        assertNotNull(accountResponse.getId());
        assertNotNull(accountResponse.getDocumentNumber());
    }

    @Test
    public void getAccountSuccess() {
        final var accountRequestMock = createAccountEntityMock();
        when(accountRepositoryImpl.getById(any())).thenReturn(Optional.of(accountRequestMock));
        final AccountResponseDTO accountResponse = accountServiceImpl.getById(1L);
        assertNotNull(accountResponse);
        assertNotNull(accountResponse.getId());
        assertNotNull(accountResponse.getDocumentNumber());
    }

    @Test
    public void getAccountException() {
        final var accountRequestMock = createAccountEntityMock();
        when(accountRepositoryImpl.getById(any())).thenReturn(Optional.of(accountRequestMock));
        assertThrows(InvalidAccountException.class, () -> accountServiceImpl.getById(null));
    }

    @Test
    public void getAccountExceptionEmpty() {
        when(accountRepositoryImpl.getById(any())).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundError.class, () -> accountServiceImpl.getById(1L));
    }

    public AccountEntity createAccountEntityMock() {
        return AccountEntity.builder().id(1L).documentNumber("12345678900").creditLimit(1000D).build();
    }

    public static AccountRequestDTO createAccountRequestDTOMock() {
        return AccountRequestDTO.builder().documentNumber("12345678900").creditLimit(1000D).build();
    }
}
