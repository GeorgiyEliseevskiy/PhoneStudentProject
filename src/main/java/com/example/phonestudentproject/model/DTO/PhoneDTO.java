package com.example.phonestudentproject.model.DTO;

import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;
import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.Enum.RegionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PhoneDTO {

    @Schema(description = "Номер телефона без кода")
    private String phoneNumber;
    @Schema(description = "Баланс")
    private BalanceDTO balance;
    @Schema(description = "Вероятность поступления")
    private String probability;
    @Schema(description = "Оператор")
    private String operator;
    @Schema(description = "Статус телефона")
    private PhoneStatusEnum status;
    @Schema(description = "Регион")
    private RegionEnum region;
}
