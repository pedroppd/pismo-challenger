package com.pismo.customers.application.controllers;

import com.pismo.customers.application.controllers.dto.request.AccountRequestDTO;
import com.pismo.customers.application.controllers.dto.response.AccountResponseDTO;
import com.pismo.customers.domain.adapters.impl.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountsController {

    private final AccountServiceImpl accountService;

    private final Logger logger = LoggerFactory.getLogger(AccountsController.class);


    @PostMapping
    public ResponseEntity<AccountResponseDTO> register(@RequestBody final AccountRequestDTO accountRequestDTO,
                                                       UriComponentsBuilder uriBuilder) {
        logger.info("Starting the account creation...");
        final AccountResponseDTO accountResponse = accountService.save(accountRequestDTO);
        final URI uri = uriBuilder.path("/accounts/{accountId}").buildAndExpand(accountResponse.getId()).toUri();
        logger.info("Finalizando processamento...");
        return ResponseEntity.created(uri).body(accountResponse);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> getById(@PathVariable final Long accountId) {
        logger.info("Starting account search {} ...", accountId);
        final AccountResponseDTO accountResponse = accountService.getById(accountId);
        return ResponseEntity.ok().body(accountResponse);
    }
}