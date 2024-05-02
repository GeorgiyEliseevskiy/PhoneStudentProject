package com.example.phonestudentproject.service.api;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import org.springframework.stereotype.Component;

public interface CallService {

    CallResponseDto call(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, String duration);
}
