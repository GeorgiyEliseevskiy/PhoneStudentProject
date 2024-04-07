package com.example.phonestudentproject.service.api.Balance;

import com.example.phonestudentproject.model.DTO.PhoneDTO;

import java.math.BigDecimal;

public interface BalanceService {

        BigDecimal calculateBalanceAfterCall(PhoneDTO phoneDTO, String duration);


}
