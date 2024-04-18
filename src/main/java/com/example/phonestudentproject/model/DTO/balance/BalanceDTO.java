package com.example.phonestudentproject.model.DTO.balance;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.model.entity.balance.BalanceOperation;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BalanceDTO {

    private BigDecimal balance;
    private List<BalanceOperationDTO> historyOperation;
}
