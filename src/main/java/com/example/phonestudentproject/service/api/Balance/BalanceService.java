package com.example.phonestudentproject.service.api.Balance;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;

import java.math.BigDecimal;

public interface BalanceService {

    BigDecimal calculateBalanceAfterCall(PhoneDTO phoneDTO, String duration);

    BalanceDTO getBalanceFromPhoneNumber(String phoneNumber);

    void depositBalance(String sum, String phoneNumber);
}
