package com.pismo.customers.application.controllers.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDTO {
    private Long fromIdAccount;
    private Double transferValue;
    private Long toIdAccount;
}
