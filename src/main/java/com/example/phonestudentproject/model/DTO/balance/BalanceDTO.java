package com.example.phonestudentproject.model.DTO.balance;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BalanceDTO {

    private BigDecimal balance;
    private List<BalanceOperationDTO> historyOperation;
}
