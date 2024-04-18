package com.example.phonestudentproject.controller;

import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;
import com.example.phonestudentproject.service.api.Balance.BalanceService;
import com.example.phonestudentproject.service.impl.Balance.BalanceServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;
    @GetMapping("/getBalance")
    public BalanceDTO getBalance(@RequestParam String phoneNumber) {
        return balanceService.getBalanceFromPhoneNumber(phoneNumber);
    }

    @PutMapping("/deposit")
    public void depositBalance(@RequestParam String sum, @RequestParam String phoneNumber) {
        balanceService.depositBalance(sum, phoneNumber);
    }
}
