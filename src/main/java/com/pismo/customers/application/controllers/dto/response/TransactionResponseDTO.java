package com.pismo.customers.application.controllers.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TransactionResponseDTO {

    @JsonProperty("transaction_id")
    private Long id;

    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("operation_type_id")
    private Integer operationTypeId;

    private Double amount;
}
