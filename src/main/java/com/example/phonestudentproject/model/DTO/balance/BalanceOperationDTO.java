package com.example.phonestudentproject.model.DTO.balance;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BalanceOperationDTO {

    private String operationDate;
    private OffsetDateTime modifyDate;
}
