package com.example.phonestudentproject.service.impl;

import com.example.phonestudentproject.exception.InfMsg;
import com.example.phonestudentproject.exception.PhoneException;
import com.example.phonestudentproject.mapper.PhoneMapper;
import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.RegistrationDTO;
import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.Enum.RegionEnum;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.repository.PhoneRepository;
import com.example.phonestudentproject.service.api.PhoneService;
import com.example.phonestudentproject.service.api.utils.PhoneServiceUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Реализация интерфейса для работы с телефоном.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final PhoneServiceUtils phoneServiceUtils;
    private final PhoneMapper phoneMapper;
    private final CallServiceImpl callService;

    @Override
    public CallResponseDto call(String phoneNumberFrom, String phoneNumberTo, String duration) {

        PhoneDTO phoneDtoFrom = buildPhoneDTO(phoneNumberFrom);
        PhoneDTO phoneDtoTo = buildPhoneDTO(phoneNumberTo);

        return callService.call(phoneDtoFrom, phoneDtoTo, duration);
    }

    private PhoneDTO buildPhoneDTO(String phoneNumber) {
        Phone phone = phoneServiceUtils.getPhoneByPhoneNumber(phoneNumber);
        return phoneMapper.toDto(phone);
    }

    @Override
    public Phone createPhoneNumber(RegistrationDTO registrationDTO) {

        Optional<Phone> phoneByPhoneNumber = phoneRepository.findPhoneByPhoneNumber(registrationDTO.getPhoneNumber());

        if (!phoneByPhoneNumber.isPresent()) {
            PhoneDTO phoneDTO = new PhoneDTO().setPhoneNumber(registrationDTO.getPhoneNumber())
                    .setRegion(RegionEnum.fromValue(registrationDTO.getRegion()))
                    .setStatus(PhoneStatusEnum.WAITING)
                    .setOperator(registrationDTO.getOperator())
                    .setBalance(registrationDTO.getBalanceDTO());


            Phone entity = phoneMapper.toEntity(phoneDTO);

            return phoneRepository.save(entity);

        } else {
            throw new PhoneException(InfMsg.PHONE_ALREADY_EXIST);
        }
    }

    @Override
    public void delete(String phoneNumber) {

        Optional<Phone> phoneByPhoneNumber = phoneRepository.findPhoneByPhoneNumber(phoneNumber);

        if (phoneByPhoneNumber.isPresent()) {
            phoneRepository.delete(phoneByPhoneNumber.get());
        } else {
            throw new PhoneException(InfMsg.PHONE_NOT_FOUND);
        }


    }
}
