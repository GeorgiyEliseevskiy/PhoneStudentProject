package com.example.phonestudentproject.model.DTO.balance;

import io.swagger.v3.oas.annotations.media.Schema;

public class BalanceOperationDTO {

    @Schema(description = "Время выполнения операции", example = "2024-04-30T13:57:11")
    private String timeOperation;
}
