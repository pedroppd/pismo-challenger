package com.pismo.customers.domain.adapters.impl;

import com.pismo.customers.application.controllers.dto.request.AccountRequestDTO;
import com.pismo.customers.application.controllers.dto.response.AccountResponseDTO;
import com.pismo.customers.infra.adapters.entities.AccountEntity;
import com.pismo.customers.infra.adapters.repositories.AccountRepositoryImpl;
import com.pismo.customers.infra.configuration.exception.AccountNotFoundError;
import com.pismo.customers.infra.configuration.exception.InvalidAccountException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
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
        Assert.assertNotNull(accountResponse);
        Assert.assertNotNull(accountResponse.getId());
        Assert.assertNotNull(accountResponse.getDocumentNumber());
    }

    @Test
    public void getAccountSuccess() {
        final var accountRequestMock = createAccountEntityMock();
        when(accountRepositoryImpl.getById(any())).thenReturn(Optional.of(accountRequestMock));
        final AccountResponseDTO accountResponse = accountServiceImpl.getById(1L);
        Assert.assertNotNull(accountResponse);
        Assert.assertNotNull(accountResponse.getId());
        Assert.assertNotNull(accountResponse.getDocumentNumber());
    }

    @Test(expected = InvalidAccountException.class)
    public void getAccountException() {
        final var accountRequestMock = createAccountEntityMock();
        when(accountRepositoryImpl.getById(any())).thenReturn(Optional.of(accountRequestMock));
        accountServiceImpl.getById(null);
    }

    @Test(expected = AccountNotFoundError.class)
    public void getAccountExceptionEmpty() {
        when(accountRepositoryImpl.getById(any())).thenReturn(Optional.empty());
        accountServiceImpl.getById(1L);
    }

    public AccountEntity createAccountEntityMock() {
        return AccountEntity.builder().id(1L).documentNumber("12345678900").build();
    }

    public static AccountRequestDTO createAccountRequestDTOMock() {
        return AccountRequestDTO.builder().documentNumber("12345678900").build();
    }
}
