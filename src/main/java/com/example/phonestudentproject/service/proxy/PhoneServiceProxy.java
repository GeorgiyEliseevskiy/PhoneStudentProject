package com.example.phonestudentproject.service.proxy;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.RegistrationDTO;
import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.service.api.PhoneService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class PhoneServiceProxy implements PhoneService{

    private final PhoneService phoneService;

    public PhoneServiceProxy(PhoneService phoneServiceImpl) {
        this.phoneService = phoneServiceImpl;
    }

    @Override
    public CallResponseDto call(String phoneNumberFrom, String phoneNumberTo, String duration) {
        return phoneService.call(phoneNumberFrom, phoneNumberTo, duration);
    }

    @Override
    public PhoneDTO createPhoneNumber(RegistrationDTO registrationDTO) {
        return phoneService.createPhoneNumber(registrationDTO);
    }

    @Override
    public void delete(String phoneNumber) {

    }

    @Override
    public boolean checkPhoneStatus(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo) {
        return phoneService.checkPhoneStatus(phoneDtoFrom, phoneDtoTo);
    }

    @Override
    public Phone getPhoneByPhoneNumber(String phoneNumber) {
        return phoneService.getPhoneByPhoneNumber(phoneNumber);
    }
}
