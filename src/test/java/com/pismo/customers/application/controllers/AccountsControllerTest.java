package com.pismo.customers.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.customers.application.controllers.dto.request.AccountRequestDTO;
import com.pismo.customers.application.controllers.dto.response.AccountResponseDTO;
import com.pismo.customers.infra.configuration.exception.ApiError;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @Order(1)
    public void stage1_shouldReturnNewAccount() throws Exception {
        final String EXPECTED_RESULT = "1234567";
        final AccountRequestDTO request = AccountRequestDTO.builder().creditLimit(1000D).documentNumber("1234567").build();

        final String requestAsString = mapper.writeValueAsString(request);

        final MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestAsString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        final String content = result.getResponse().getContentAsString();

        final AccountResponseDTO accountResponseDTO = mapper.readValue(content, AccountResponseDTO.class);

        assertNotNull(accountResponseDTO.getId());
        assertNotNull(accountResponseDTO.getDocumentNumber());
        assertEquals(EXPECTED_RESULT, accountResponseDTO.getDocumentNumber());
    }

    @Test
    @Order(2)
    public void stage2_shouldReturnAccount() throws Exception {
        final String EXPECTED_RESULT = "1234567";
        final Long accountId = 1L;

        final MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/accounts/{accountId}", accountId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        final String content = result.getResponse().getContentAsString();

        final AccountResponseDTO accountResponseDTO = mapper.readValue(content, AccountResponseDTO.class);

        assertNotNull(accountResponseDTO.getId());
        assertNotNull(accountResponseDTO.getDocumentNumber());
        assertEquals(accountId, accountResponseDTO.getId());
        assertEquals(EXPECTED_RESULT, accountResponseDTO.getDocumentNumber());
    }

    @Test
    @Order(3)
    public void stage3_shouldReturnError() throws Exception {
        final Long accountId = 2L;

        final MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/accounts/{accountId}", accountId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        final String content = result.getResponse().getContentAsString();

        final ApiError apiError = mapper.readValue(content, ApiError.class);

        assertTrue(HttpStatus.NOT_FOUND.value() == apiError.code());
        assertEquals(HttpStatus.NOT_FOUND.name(), apiError.status());
    }

}
