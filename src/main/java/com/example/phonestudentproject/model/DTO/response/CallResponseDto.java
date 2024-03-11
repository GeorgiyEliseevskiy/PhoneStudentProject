package com.example.phonestudentproject.model.DTO.response;

import com.example.phonestudentproject.model.DTO.OperatorDto;
import com.example.phonestudentproject.model.DTO.PhoneDTO;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class CallResponseDto {

    private Duration timeCall;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String phoneFrom;
    private String phoneTo;
    private String notes;
    private boolean isSuccessfulCall;
    private String spentMoney;
    private PhoneDTO phoneDtoFrom;
    private PhoneDTO phoneDtoTo;

}
