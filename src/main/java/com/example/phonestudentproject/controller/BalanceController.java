package com.example.phonestudentproject.controller;

import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;
import com.example.phonestudentproject.model.entity.balance.Balance;
import com.example.phonestudentproject.service.impl.decorator.BalanceServiceDecorator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/balance")
@Tag(name = "API взаимодействие с балансом")
public class BalanceController {

    private final BalanceServiceDecorator balanceServiceDecorator;
    @GetMapping("/getBalance")
    @Operation(summary = "Получить баланс по номеру телефона")
    public BalanceDTO getBalance(@RequestParam String phoneNumber) {
        return balanceServiceDecorator.getBalanceFromPhoneNumber(phoneNumber);
    }

    @PutMapping("/deposit")
    @Operation(summary = "Пополнить баланс телефона")
    public void depositBalance(@RequestParam String sum, @RequestParam String phoneNumber) {
        balanceServiceDecorator.depositBalance(sum, phoneNumber);
    }

    @PostMapping("/createBalance")
    @Operation(summary = "Создать баланс")
    public Balance createBalance(@RequestBody BalanceDTO balanceDTO) {
        return balanceServiceDecorator.createBalance(balanceDTO);
    }
}
