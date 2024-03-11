package com.example.phonestudentproject.service.impl;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.service.api.BalanceService;
import com.example.phonestudentproject.service.api.CallService;
import com.example.phonestudentproject.service.api.PhoneService;
import com.example.phonestudentproject.service.api.ProbabilityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class CallServiceImpl implements CallService {
    private final PhoneService phoneService; //TODO proxy - избегаем циклической зависимости
    private final ProbabilityService probabilityService;
    private final BalanceService balanceService;
    @Override
    public CallResponseDto call(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, String duration) {

        makeCall(phoneDtoFrom, phoneDtoTo, duration);
        return buildCallResponseDTO();
    }

    private CallResponseDto makeCall(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, String duration) {
        boolean checkPhoneStatus = phoneService.checkPhoneStatus(phoneDtoFrom, phoneDtoTo);
        CallResponseDto callResponseDto = new CallResponseDto();
        LocalDateTime startTime;
        LocalDateTime endTime;
        double probabilityCall = Double.parseDouble(probabilityService.getProbabilitySerice(phoneDtoTo));
        if (checkPhoneStatus && probabilityCall > 60) {
            String phoneNumberFrom = buildFullPhoneNumber(phoneDtoFrom);
            String phoneNumberTo = buildFullPhoneNumber(phoneDtoTo);

            Phone phoneFrom = phoneService.getPhoneByPhoneNumber(phoneDtoFrom.getPhoneNumber());
            Phone phoneTo = phoneService.getPhoneByPhoneNumber(phoneDtoFrom.getPhoneNumber());
            startTime = LocalDateTime.now();

            //TODO Многопоточность, вызов 5 секунд
            phoneFrom.setStatus(PhoneStatusEnum.CALL);
            phoneTo.setStatus(PhoneStatusEnum.CALL);
            log.info("Status: " + phoneNumberFrom + " is " + phoneFrom.getStatus());
            log.info("Status: " + phoneNumberTo + " is " + phoneTo.getStatus());
            log.info("Вызов абонента: " + phoneNumberTo);

            phoneDtoFrom.setStatus(PhoneStatusEnum.CONVERSATION);
            phoneDtoTo.setStatus(PhoneStatusEnum.CONVERSATION);

            int durations = Integer.parseInt(duration);

            //TODO многопоточность
            phoneDtoFrom.setStatus(PhoneStatusEnum.WAITING);
            phoneDtoTo.setStatus(PhoneStatusEnum.WAITING);
            endTime = LocalDateTime.now();
            log.info(String.format("Вызов абонента %s завершен. Разговор продлился: %s", phoneTo, duration));
            log.info("Status: " + phoneNumberFrom + " is " + phoneFrom.getStatus());
            log.info("Status: " + phoneNumberTo + " is " + phoneTo.getStatus());

            callResponseDto.setStartTime(startTime);
            callResponseDto.setEndTime(endTime);
            callResponseDto.setTimeCall(Duration.between(endTime, startTime));
            callResponseDto.setPhoneFrom(phoneNumberFrom);
            callResponseDto.setPhoneTo(phoneNumberTo);
            callResponseDto.setPhoneDtoFrom(phoneDtoFrom);
            callResponseDto.setPhoneDtoTo(phoneDtoTo);
            callResponseDto.setSuccessfulCall(true);
        } else {

        }

        return callResponseDto;
    }




    private String buildFullPhoneNumber(PhoneDTO phoneDtoTo) {
        String phoneNumber = phoneDtoTo.getPhoneNumber();
        String value = phoneDtoTo.getRegion().getValue();
        return value + " " + phoneNumber;
    }

    private CallResponseDto buildCallResponseDTO(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, CallResponseDto beginResponse) {
        CallResponseDto callResponseDto = new CallResponseDto();

        String phoneNumberFrom = buildFullPhoneNumber(phoneDtoFrom);
        String phoneNumberTo = buildFullPhoneNumber(phoneDtoTo);
        return null;
    }
}
