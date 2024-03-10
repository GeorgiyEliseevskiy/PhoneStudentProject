package com.example.phonestudentproject.service.impl;

import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.service.api.CallService;
import com.example.phonestudentproject.service.api.PhoneService;
import com.example.phonestudentproject.service.api.ProbabilityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class CallServiceImpl implements CallService {
    private final PhoneService phoneService; //TODO proxy - избегаем циклической зависимости
    private final ProbabilityService probabilityService;
    @Override
    public CallResponseDto call(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, String duration) {

        makeCall(phoneDtoFrom, phoneDtoTo, duration);
        return buildCallResponseDTO();
    }

    private void makeCall(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, String duration) {
        boolean checkPhoneStatus = phoneService.checkPhoneStatus(phoneDtoFrom, phoneDtoTo);

        double isProbabilityAllows = Double.parseDouble(probabilityService.getProbabilitySerice(phoneDtoTo));
        if (checkPhoneStatus) {

            String phoneNumberFrom = buildFullPhoneNumber(phoneDtoFrom);
            String phoneNumberTo = buildFullPhoneNumber(phoneDtoTo);

            Phone phoneFrom = phoneService.getPhoneByPhoneNumber(phoneDtoFrom.getPhoneNumber());
            Phone phoneTo = phoneService.getPhoneByPhoneNumber(phoneDtoFrom.getPhoneNumber());


            //TODO Многопоточность, вызов 5 секунд
            phoneFrom.setStatus(PhoneStatusEnum.CALL);
            phoneTo.setStatus(PhoneStatusEnum.CALL);
            log.info("Вызов абонента: " + phoneTo);

            phoneDtoFrom.setStatus(PhoneStatusEnum.CONVERSATION);
            phoneDtoTo.setStatus(PhoneStatusEnum.CONVERSATION);

            int durations = Integer.parseInt(duration);

            //TODO многопоточность





        }
    }




    private String buildFullPhoneNumber(PhoneDTO phoneDtoTo) {
        String phoneNumber = phoneDtoTo.getPhoneNumber();
        String value = phoneDtoTo.getRegion().getValue();
        return value + " " + phoneNumber;
    }

    private CallResponseDto buildCallResponseDTO() {

        return null;
    }
}
