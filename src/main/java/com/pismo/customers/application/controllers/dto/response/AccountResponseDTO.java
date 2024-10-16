package com.pismo.customers.application.controllers.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountResponseDTO {

    @JsonProperty("account_id")
    private Long id;

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("credit_limit")
    private Double creditLimit;


}
