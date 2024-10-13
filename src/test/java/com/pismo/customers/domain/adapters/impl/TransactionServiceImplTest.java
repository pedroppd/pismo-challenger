package com.pismo.customers.domain.adapters.impl;

import com.pismo.customers.application.controllers.dto.request.TransactionRequestDTO;
import com.pismo.customers.application.controllers.dto.response.TransactionResponseDTO;
import com.pismo.customers.domain.enums.OperationTypeEnum;
import com.pismo.customers.infra.adapters.entities.AccountEntity;
import com.pismo.customers.infra.adapters.entities.TransactionEntity;
import com.pismo.customers.infra.adapters.repositories.AccountRepositoryImpl;
import com.pismo.customers.infra.adapters.repositories.TransactionRepositoryImpl;
import com.pismo.customers.infra.configuration.exception.SaveTransactionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TransactionServiceImplTest {

    @Mock
    private AccountRepositoryImpl accountRepositoryImpl;

    @Mock
    private TransactionRepositoryImpl transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    @Test(expected = AccountNotFoundException.class)
    public void getTransactionAccountError() throws AccountNotFoundException {
        final var transactionRequest = createTransactionRequestDTOMock(1L, 350.00, 2);
        transactionServiceImpl.save(transactionRequest);
        when(accountRepositoryImpl.getById(any())).thenReturn(Optional.empty());
    }

    @Test(expected = SaveTransactionException.class)
    public void saveTransactionError() throws AccountNotFoundException {
        final Optional<AccountEntity> accountEntity = Optional.of(AccountEntity.builder().id(1L).documentNumber("1234567").build());
        when(accountRepositoryImpl.getById(any())).thenReturn(accountEntity);
        final var transactionRequest = createTransactionRequestDTOMock(1L, 350.00, 2);
        transactionServiceImpl.save(transactionRequest);
    }

    @Test
    public void saveTransactionSuccess() throws AccountNotFoundException {
        final Optional<AccountEntity> accountEntity = Optional.of(AccountEntity.builder().id(1L).documentNumber("1234567").build());
        final Optional<TransactionEntity> transaction = Optional.of(TransactionEntity.builder().id(1L).account(accountEntity.get()).operationType(OperationTypeEnum.INSTALLMENT_PURCHASE).amount(-355.00).eventDate("").build());
        when(accountRepositoryImpl.getById(any())).thenReturn(accountEntity);
        when(transactionRepository.save(any())).thenReturn(transaction);
        final var transactionRequest = createTransactionRequestDTOMock(1L, 355.00, 2);
        final var transactionResponse = transactionServiceImpl.save(transactionRequest);
        assertNotNull(transactionResponse);
        assertTrue(transactionResponse.getAmount() < 0);
    }

    @Test
    public void saveTransactionSuccessAndAmountValueisPositive() throws AccountNotFoundException {
        final Optional<AccountEntity> accountEntity = Optional.of(AccountEntity.builder().id(1L).documentNumber("1234567").build());
        final Optional<TransactionEntity> transaction = Optional.of(TransactionEntity.builder().id(1L).account(accountEntity.get()).operationType(OperationTypeEnum.PAYMENT).amount(355.00).eventDate("").build());
        when(accountRepositoryImpl.getById(any())).thenReturn(accountEntity);
        when(transactionRepository.save(any())).thenReturn(transaction);
        final var transactionRequest = createTransactionRequestDTOMock(1L, 355.00, 4);
        final var transactionResponse = transactionServiceImpl.save(transactionRequest);
        assertNotNull(transactionResponse);
        assertTrue(transactionResponse.getAmount() > 0);
    }

    public Optional<AccountEntity> createAccountEntityMock() {
        return Optional.of(AccountEntity.builder().id(1L).documentNumber("12345678900").build());
    }

    public static TransactionResponseDTO createTransactionResponseDTOMock(final Long id, final Double amount, final Integer operationType, final Long accountId) {
        return TransactionResponseDTO.builder().id(id).amount(amount)
                .operationTypeId(operationType).accountId(accountId).build();
    }

    public static TransactionRequestDTO createTransactionRequestDTOMock(final Long id,
                                                                        final Double amount,
                                                                        final Integer operationType) {
        return TransactionRequestDTO.builder()
                .accountId(id)
                .amount(amount)
                .operationTypeId(operationType)
                .build();
    }
}
