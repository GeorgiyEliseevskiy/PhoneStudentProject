package com.example.phonestudentproject.service.api;

import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.model.DTO.PhoneDTO;

public interface CallService {
    CallResponseDto call(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, String duration);
}
