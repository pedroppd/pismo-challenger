package com.pismo.customers.application.controllers.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountResponseDTO {
    private Long id;

    private String documentNumber;
}
