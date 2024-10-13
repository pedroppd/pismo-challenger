package com.pismo.customers.application.controllers.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {
    private String documentNumber;
}
