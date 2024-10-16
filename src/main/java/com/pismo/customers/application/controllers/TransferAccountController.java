package com.pismo.customers.application.controllers;

import com.pismo.customers.application.controllers.dto.request.AccountRequestDTO;
import com.pismo.customers.application.controllers.dto.request.TransferRequestDTO;
import com.pismo.customers.application.controllers.dto.response.AccountResponseDTO;
import com.pismo.customers.application.controllers.dto.response.TransferResponseDTO;
import com.pismo.customers.domain.adapters.impl.TransferServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
@AllArgsConstructor
public class TransferAccountController {

    private final TransferServiceImpl transferServiceImpl;

    private final Logger logger = LoggerFactory.getLogger(TransferAccountController.class);


    @PostMapping
    public ResponseEntity<TransferResponseDTO> register(@RequestBody final TransferRequestDTO transferRequestDTO) {
        logger.info("Starting the transfer ...");
        final var transferResponse = transferServiceImpl.save(transferRequestDTO);
        logger.info("Finish transfer ...");
        return ResponseEntity.status(HttpStatus.CREATED).body(transferResponse);
    }
}