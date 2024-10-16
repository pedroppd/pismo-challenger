package com.pismo.customers.application.controllers.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TransferResponseDTO {

    @JsonProperty("transaction_from")
    private TransactionResponseDTO transactionFrom;

    @JsonProperty("transaction_to")
    private TransactionResponseDTO transactionTo;

    private Double balance;
}
