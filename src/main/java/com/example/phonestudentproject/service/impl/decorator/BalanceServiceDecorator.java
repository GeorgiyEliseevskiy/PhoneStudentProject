package com.example.phonestudentproject.service.impl.decorator;

import com.example.phonestudentproject.mapper.BalanceMapper;
import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;
import com.example.phonestudentproject.model.entity.balance.Balance;
import com.example.phonestudentproject.repository.BalanceRepository;
import com.example.phonestudentproject.service.api.Balance.BalanceService;
import com.example.phonestudentproject.service.impl.Balance.BalanceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceServiceDecorator implements BalanceService {

    private final BalanceServiceImpl balanceService;
    private final BalanceMapper balanceMapper;
    private final BalanceRepository balanceRepository;

    @Override
    public BigDecimal calculateBalanceAfterCall(PhoneDTO phoneDTO, String duration) {
        return balanceService.calculateBalanceAfterCall(phoneDTO, duration);
    }

    @Override
    public BalanceDTO getBalanceFromPhoneNumber(String phoneNumber) {
        return balanceService.getBalanceFromPhoneNumber(phoneNumber);
    }

    @Override
    public void depositBalance(String sum, String phoneNumber) {
        balanceService.depositBalance(sum, phoneNumber);
    }

    public Balance createBalance(BalanceDTO balanceDTO) {
        Balance entity = balanceMapper.toEntity(balanceDTO);
        return balanceRepository.save(entity);
    }
}

