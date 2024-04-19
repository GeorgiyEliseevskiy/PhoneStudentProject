package com.example.phonestudentproject.service.impl;

import com.example.phonestudentproject.exception.InfMsg;
import com.example.phonestudentproject.exception.PhoneException;
import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.service.api.Balance.BalanceService;
import com.example.phonestudentproject.service.api.CallService;
import com.example.phonestudentproject.service.api.PhoneService;
import com.example.phonestudentproject.service.api.ProbabilityService;
import com.example.phonestudentproject.service.proxy.PhoneServiceProxy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class CallServiceImpl implements CallService {
    private final PhoneService phoneServiceProxy; //TODO proxy - избегаем циклической зависимости
    private final ProbabilityService probabilityService;
    private final BalanceService balanceService;

    @Override
    public CallResponseDto call(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, String duration) {

        return makeCall(phoneDtoFrom, phoneDtoTo, duration);
    }

    private CallResponseDto makeCall(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, String duration) {

        boolean checkPhoneStatus = phoneServiceProxy.checkPhoneStatus(phoneDtoFrom, phoneDtoTo);

        LocalDateTime startTime;
        LocalDateTime endTime;

        double probabilityCall = Double.parseDouble(probabilityService.getProbabilityCall(phoneDtoTo));

        if (checkPhoneStatus && probabilityCall > 40) {
            String phoneNumberFrom = buildFullPhoneNumber(phoneDtoFrom);
            String phoneNumberTo = buildFullPhoneNumber(phoneDtoTo);

            Phone phoneFrom = phoneServiceProxy.getPhoneByPhoneNumber(phoneDtoFrom.getPhoneNumber());
            Phone phoneTo = phoneServiceProxy.getPhoneByPhoneNumber(phoneDtoFrom.getPhoneNumber());

            startTime = LocalDateTime.now();

            log.info("Вызов абонента: " + phoneNumberTo);
            makeCallBetweenPhones(phoneFrom, phoneTo, phoneNumberFrom, phoneNumberTo);

            log.info(String.format("Cоедининение с абнонентом %s было установленно ", phoneNumberTo));
            startAndSustainConversation(phoneDtoFrom, phoneDtoTo, duration);

            setAndLogStatusForWaiting(phoneDtoFrom, phoneDtoTo, phoneNumberFrom, phoneNumberTo);

            log.info(String.format("Вызов абонента %s завершен. Разговор продлился: %s", phoneTo, duration));
            endTime = LocalDateTime.now();

            return new CallResponseDto.Builder()
                    .startTime(startTime)
                    .endTime(endTime)
                    .timeCall(Duration.between(endTime, startTime))
                    .phoneFrom(phoneNumberFrom)
                    .phoneTo(phoneNumberTo)
                    .phoneDtoFrom(phoneDtoFrom)
                    .phoneDtoTo(phoneDtoTo)
                    .isSuccessfulCall(true)
                    .notes(InfMsg.CALL_WAS_SUCCESSFUL)
                    .build();

        } else {
            endTime = LocalDateTime.now();
            return new  CallResponseDto.Builder()
                    .startTime(endTime)
                    .endTime(endTime)
                    .timeCall(Duration.between(endTime, endTime))
                    .notes(InfMsg.CALL_WAS_UNSUCCESSFUL)
                    .build();
        }
    }

    private static void setAndLogStatusForWaiting(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, String phoneNumberFrom, String phoneNumberTo) {
        phoneDtoFrom.setStatus(PhoneStatusEnum.WAITING);
        phoneDtoTo.setStatus(PhoneStatusEnum.WAITING);

        log.info("Status: " + phoneNumberFrom + " is " + phoneDtoFrom.getStatus());
        log.info("Status: " + phoneNumberTo + " is " + phoneDtoTo.getStatus());
    }

    private static void startAndSustainConversation(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, String duration) {
        phoneDtoFrom.setStatus(PhoneStatusEnum.CONVERSATION);
        phoneDtoTo.setStatus(PhoneStatusEnum.CONVERSATION);

        int durationMilliSecond = Integer.parseInt(duration);

        try {
            Thread.sleep(durationMilliSecond);
        } catch (InterruptedException e) {
            throw new PhoneException(InfMsg.CONVERSATION_WAS_INTERRUPT);
        }
    }

    private static void makeCallBetweenPhones(Phone phoneFrom, Phone phoneTo, String phoneNumberFrom, String phoneNumberTo) {
        phoneFrom.setStatus(PhoneStatusEnum.CALL);
        phoneTo.setStatus(PhoneStatusEnum.CALL);

        log.info("Status: " + phoneNumberFrom + " is " + phoneFrom.getStatus());
        log.info("Status: " + phoneNumberTo + " is " + phoneTo.getStatus());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new PhoneException(InfMsg.CONNECTION_NOT_BE_ESTABLISHED);
        }
    }

    private String buildFullPhoneNumber(PhoneDTO phoneDtoTo) {
        String phoneNumber = phoneDtoTo.getPhoneNumber();
        String value = phoneDtoTo.getRegion().getValue();
        return value + " " + phoneNumber;
    }
}
