package com.pismo.customers.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.customers.application.controllers.dto.request.TransactionRequestDTO;
import com.pismo.customers.application.controllers.dto.response.TransactionResponseDTO;
import com.pismo.customers.domain.enums.OperationTypeEnum;
import com.pismo.customers.infra.adapters.entities.AccountEntity;
import com.pismo.customers.infra.adapters.entities.TransactionEntity;
import com.pismo.customers.infra.adapters.repositories.AccountRepositoryImpl;
import com.pismo.customers.infra.adapters.repositories.TransactionRepositoryImpl;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AccountRepositoryImpl accountRepositoryImpl;

    @MockBean
    private TransactionRepositoryImpl transactionRepositoryImpl;

    @Test
    public void stage1_shouldReturnNewTransaction() throws Exception {
        final Optional<AccountEntity> mockAccount = Optional.of(AccountEntity.builder().id(1L).documentNumber("1234567").build());
        final Optional<TransactionEntity> mockTransaction = Optional.of(TransactionEntity.builder().account(mockAccount.get()).id(1L).amount(-1000.0).operationType(OperationTypeEnum.INSTALLMENT_PURCHASE).build());

        when(accountRepositoryImpl.getById(any())).thenReturn(mockAccount);
        when(transactionRepositoryImpl.save(any())).thenReturn(mockTransaction);

        final TransactionRequestDTO request = TransactionRequestDTO.builder()
                .accountId(1L).amount(1000.0).operationTypeId(2).build();

        final String requestAsString = mapper.writeValueAsString(request);

        final MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestAsString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        final String content = result.getResponse().getContentAsString();
        final TransactionResponseDTO accountResponseDTO = mapper.readValue(content, TransactionResponseDTO.class);

        assertNotNull(accountResponseDTO.getId());
        assertTrue(accountResponseDTO.getAmount() < 0);
    }
}
