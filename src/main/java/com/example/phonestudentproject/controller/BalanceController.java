package com.example.phonestudentproject.controller;

import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;
import com.example.phonestudentproject.service.api.Balance.BalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/balance")
@Tag(name = "API взаимодействие с балансом")
public class BalanceController {

    private final BalanceService balanceService;
    @GetMapping("/getBalance")
    @Operation(summary = "Получить баланс по номеру телефона")
    public BalanceDTO getBalance(@RequestParam String phoneNumber) {
        return balanceService.getBalanceFromPhoneNumber(phoneNumber);
    }

    @PutMapping("/deposit")
    @Operation(summary = "Пополнить баланс телефона")
    public void depositBalance(@RequestParam String sum, @RequestParam String phoneNumber) {
        balanceService.depositBalance(sum, phoneNumber);
    }
}
