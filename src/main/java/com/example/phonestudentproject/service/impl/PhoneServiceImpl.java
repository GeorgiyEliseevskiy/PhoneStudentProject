package com.example.phonestudentproject.service.impl;

import com.example.phonestudentproject.exception.InfMsg;
import com.example.phonestudentproject.exception.PhoneException;
import com.example.phonestudentproject.mapper.PhoneMapper;
import com.example.phonestudentproject.model.DTO.RegistrationDTO;
import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.repository.PhoneRepository;
import com.example.phonestudentproject.service.api.CallService;
import com.example.phonestudentproject.service.api.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final CallService callService;
    private final PhoneMapper phoneMapper;

    @Override
    public CallResponseDto call(String phoneNumberFrom, String phoneNumberTo, String duration) {

        PhoneDTO phoneDtoFrom = buildPhoneDTO(phoneNumberFrom);
        PhoneDTO phoneDtoTo = buildPhoneDTO(phoneNumberTo);

        CallResponseDto callResponseDto = callService.call(phoneDtoFrom, phoneDtoTo, duration);
        Phone entityPhoneTo = phoneMapper.toEntity(callResponseDto.getPhoneDtoTo());
        Phone entityPhoneFrom = phoneMapper.toEntity(callResponseDto.getPhoneDtoFrom());

        phoneRepository.saveAll(List.of(entityPhoneTo, entityPhoneFrom));
        return callResponseDto;
    }

    private PhoneDTO buildPhoneDTO(String phoneNumber) {
        Phone phone = getPhoneByPhoneNumber(phoneNumber);
        return phoneMapper.toDto(phone);
    }

    @Override
    public boolean checkPhoneStatus(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo) {
        PhoneStatusEnum statusPhoneFrom = phoneDtoFrom.getStatus();
        PhoneStatusEnum statusPhoneTo = phoneDtoTo.getStatus();

        return PhoneStatusEnum.WAITING.equals(statusPhoneFrom) && PhoneStatusEnum.WAITING.equals(statusPhoneTo);
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
    public PhoneDTO createPhoneNumber(RegistrationDTO registrationDTO) {
        return null;
    }

    @Override
    public void delete(String phoneNumber) {

    }
}
