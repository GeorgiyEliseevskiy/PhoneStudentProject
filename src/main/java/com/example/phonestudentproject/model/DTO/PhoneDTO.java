package com.example.phonestudentproject.model.DTO;

import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.Enum.RegionEnum;
import lombok.Data;

import java.util.List;

@Data
public class PhoneDTO {

    private String phoneNumber;
    private BalanceDTO balance;
    private String probability;
    private OperatorDto operator;
    private PhoneStatusEnum status;
    private RegionEnum region;
    private List<String> logCalls;
}
