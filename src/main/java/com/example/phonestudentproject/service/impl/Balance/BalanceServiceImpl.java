package com.example.phonestudentproject.service.impl.Balance;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.service.api.Balance.BalanceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BalanceServiceImpl implements BalanceService {
    @Override
    public BigDecimal calculateBalanceAfterCall(PhoneDTO phoneDTO, String duration) {
        BigDecimal balance = phoneDTO.getBalance().getBalance();
        BigDecimal spentMoney = new BigDecimal(duration);
        BigDecimal result = new BigDecimal(-1);
        int comparisonResult = balance.compareTo(spentMoney);

        if (comparisonResult > 0) {
            return balance.subtract(spentMoney);
        } else {
            return result;
            //TODO аспекты ставят статус
        }
    }


    // TODO подсчет баланса во время разговора. Прерыванине разговора через аспекты (Ставим статус блокдет если баланс становится меньше 0)


}
