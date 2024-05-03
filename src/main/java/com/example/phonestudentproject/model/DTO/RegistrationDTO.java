package com.example.phonestudentproject.model.DTO;

import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ДТО содержащая информацию необходимую для регистрации телефона.
 */
@Data
public class RegistrationDTO {

    @Schema(description = "Номер телефона")
    private String phoneNumber;

    @Schema(description = "Баланс")
    private BalanceDTO balanceDTO;

    @Schema(description = "Статус")
    private String status;

    @Schema(description = "Регион")
    private String region;

    @Schema(description = "Оператор")
    private String operator;

}
