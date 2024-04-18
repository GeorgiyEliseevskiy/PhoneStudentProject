package com.example.phonestudentproject.model.DTO;

import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;
import lombok.Data;

@Data
public class RegistrationDTO {

    /**
     * Номер телефона.
     */
    private String phoneNumber;

    /**
     * Баланас.
     */
    private BalanceDTO balanceDTO;

    /**
     * Статус.
     */
    private String status;

    /**
     * Регион.
     */
    private String region;

    /**
     * Оператор.
     */
    private String operator;

}
