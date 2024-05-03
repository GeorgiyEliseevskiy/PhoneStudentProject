package com.example.phonestudentproject.service.impl.Balance;

import com.example.phonestudentproject.mapper.BalanceMapper;
import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;
import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.model.entity.balance.Balance;
import com.example.phonestudentproject.repository.BalanceRepository;
import com.example.phonestudentproject.service.api.Balance.BalanceService;
import com.example.phonestudentproject.service.api.PhoneService;
import com.example.phonestudentproject.service.api.utils.PhoneServiceUtils;
import com.example.phonestudentproject.service.impl.PhoneServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;
    private final PhoneServiceUtils phoneService;

    @Override
    @Deprecated
    public BigDecimal calculateBalanceAfterCall(PhoneDTO phoneDTO, String duration) {
        BigDecimal balance = phoneDTO.getBalance().getBalance();
        BigDecimal spentMoney = new BigDecimal(duration);
        BigDecimal result = new BigDecimal(-1);

        int comparisonResult = balance.compareTo(spentMoney);

        if (comparisonResult > 0) {
            return balance.subtract(spentMoney);
        } else {
            return result;
        }
    }

    @Override
    public BalanceDTO getBalanceFromPhoneNumber(String phoneNumber) {
        Optional<Balance> balance = balanceRepository.findBalanceByPhoneNumber(phoneNumber);
        if (balance.isPresent()) {
            balanceMapper.toDto(balance.get());
            return balanceMapper.toDto(balance.get());
        } else {
            return new BalanceDTO();
        }
    }

    @Override
    public void depositBalance(String sum, String phoneNumber) {
        Optional<Balance> balanceOptional = balanceRepository.findBalanceByPhoneNumber(phoneNumber);
        if (balanceOptional.isPresent()) {
            Balance balance = balanceOptional.get();
            BigDecimal addBalance = balance.getBalance().add(BigDecimal.valueOf(Long.parseLong(sum)));
            balanceRepository.updateSumByPhoneNumber(phoneNumber, addBalance);

            Phone phoneByPhoneNumber = phoneService.getPhoneByPhoneNumber(phoneNumber);

            if (addBalance.intValue() > 0 && PhoneStatusEnum.BLOCKED.equals(phoneByPhoneNumber.getStatus())) {
                phoneByPhoneNumber.setStatus(PhoneStatusEnum.WAITING);
                phoneService.setPhoneStatusRuntime(PhoneStatusEnum.WAITING, phoneByPhoneNumber.getId());
            }
        }
    }
}
