package com.example.phonestudentproject.service.api;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.RegistrationDTO;
import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.model.entity.Phone;

public interface PhoneService {
    CallResponseDto call(String phoneNumberFrom, String phoneNumberTo, String duration);
    PhoneDTO createPhoneNumber(RegistrationDTO registrationDTO);
    void delete(String phoneNumber);
    boolean checkPhoneStatus(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo);
    Phone getPhoneByPhoneNumber(String phoneNumber);
}
