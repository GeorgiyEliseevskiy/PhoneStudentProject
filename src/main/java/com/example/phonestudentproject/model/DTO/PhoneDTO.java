package com.example.phonestudentproject.model.DTO;

import com.example.phonestudentproject.model.DTO.balance.BalanceDTO;
import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.Enum.RegionEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PhoneDTO {

    private String phoneNumber;
    private BalanceDTO balance;
    private String probability;
    private String operator;
    /*private OperatorDto operator;*/
    private PhoneStatusEnum status;
    private RegionEnum region;
    private List<String> logCalls;
}
