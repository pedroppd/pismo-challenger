package com.pismo.customers.application.controllers.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("operation_type_id")
    private Integer operationTypeId;
    private Double amount;
}
