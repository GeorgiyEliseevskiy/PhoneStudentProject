package com.example.phonestudentproject.service.impl;

import com.example.phonestudentproject.exception.InfMsg;
import com.example.phonestudentproject.exception.PhoneException;
import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.repository.PhoneRepository;
import com.example.phonestudentproject.service.api.CallService;
import com.example.phonestudentproject.service.api.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final CallService callService;

    @Override
    public CallResponseDto call(String phoneNumberFrom, String phoneNumberTo, String duration) {
        PhoneDTO phoneDtoFrom = buildPhoneDTO(phoneNumberFrom);
        PhoneDTO phoneDtoTo = buildPhoneDTO(phoneNumberTo);

        return callService.call(phoneDtoFrom, phoneDtoTo, duration);
    }

    private PhoneDTO buildPhoneDTO(String phoneNumber) {
        Phone phone = getPhoneByPhoneNumber(phoneNumber);
        //TODO MapStruct
    }

    @Override
    public boolean checkPhoneStatus(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo) {
        PhoneStatusEnum statusPhoneFrom = phoneDtoFrom.getStatus();
        PhoneStatusEnum statusPhoneTo = phoneDtoTo.getStatus();

        if (statusPhoneFrom != PhoneStatusEnum.WAITING) {
            return false;
        } else if (statusPhoneFrom == PhoneStatusEnum.WAITING && statusPhoneTo == PhoneStatusEnum.WAITING) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Phone getPhoneByPhoneNumber(String phoneNumber) {
        Optional<Phone> phone = phoneRepository.findPhoneByPhoneNumber(phoneNumber);
        if (phone.isPresent()) {
            return phone.get();
        }
        else {
            throw new PhoneException(InfMsg.PHONE_NOT_FOUND);
        }
    }

    @Override
    public void createPhoneNumber() {

    }

    @Override
    public void delete(String phoneNumber) {

    }
}
