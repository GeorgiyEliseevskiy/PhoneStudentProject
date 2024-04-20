package com.example.phonestudentproject.service.api.utils;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.entity.Phone;

public interface PhoneServiceUtils {

    boolean checkPhoneStatus(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo);
    Phone getPhoneByPhoneNumber(String phoneNumber);
}
