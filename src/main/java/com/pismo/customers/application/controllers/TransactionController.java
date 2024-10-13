package com.pismo.customers.application.controllers;

import com.pismo.customers.application.controllers.dto.request.TransactionRequestDTO;
import com.pismo.customers.application.controllers.dto.response.TransactionResponseDTO;
import com.pismo.customers.domain.adapters.impl.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionServiceImpl transactionService;
    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);


    @PostMapping
    public ResponseEntity<TransactionResponseDTO> register(@RequestBody TransactionRequestDTO transactionRequest) throws AccountNotFoundException {
        logger.info("Starting process to save transaction");
        final TransactionResponseDTO transactionResponseDTO = transactionService.save(transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponseDTO);
    }
}