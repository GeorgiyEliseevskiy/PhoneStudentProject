package com.example.phonestudentproject.service.impl;

import com.example.phonestudentproject.exception.InfMsg;
import com.example.phonestudentproject.exception.PhoneException;
import com.example.phonestudentproject.model.DTO.ConversationDTO;
import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.service.api.CallService;
import com.example.phonestudentproject.service.api.ProbabilityService;
import com.example.phonestudentproject.service.api.utils.PhoneServiceUtils;
import com.example.phonestudentproject.service.impl.command.*;
import com.example.phonestudentproject.service.impl.facade.ConversationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Component
public class CallServiceImpl {

    private final ProbabilityService probabilityService;
    private final PhoneServiceUtils phoneServiceUtils;
    private final ConversationFacade conversationFacade;

    public CallResponseDto call(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, String duration) {
        return makeCall(phoneDtoFrom, phoneDtoTo, duration);
    }

    private CallResponseDto makeCall(PhoneDTO phoneDtoFrom, PhoneDTO phoneDtoTo, String duration) {
        boolean checkPhoneStatus = phoneServiceUtils.checkPhoneStatus(phoneDtoFrom, phoneDtoTo);

        ManagementCommandLogger logger = new ManagementCommandLogger(
                new CommandLoggerWARN(),
                new CommandLoggerDEBUG(),
                new CommandLoggerERROR(),
                new CommandLoggerInfo()
        );

        LocalDateTime startTime;
        LocalDateTime endTime;

        double probabilityCall = Double.parseDouble(probabilityService.getProbabilityCall(phoneDtoTo));

        if (checkPhoneStatus && probabilityCall >= 40) {

            ConversationDTO conversationDTO = conversationFacade.getConversationDTO(phoneDtoTo, phoneDtoFrom);

            startTime = LocalDateTime.now();

            logger.infoMessage(String.format("Вызов абонента: " + conversationDTO.getPhoneNumberTo()));

            makeCallBetweenPhones(conversationDTO.getPhoneFrom(),
                    conversationDTO.getPhoneTo(),
                    conversationDTO.getPhoneNumberFrom(),
                    conversationDTO.getPhoneNumberTo());

            logger.infoMessage(String.format("Cоедининение с абнонентом %s было установленно ", conversationDTO.getPhoneNumberTo()));

            startAndSustainConversation(conversationDTO.getPhoneFrom(),
                    conversationDTO.getPhoneTo(), duration, conversationDTO.getPhoneNumberFrom(),
                    conversationDTO.getPhoneNumberTo());

            setAndLogStatusForWaiting(conversationDTO.getPhoneFrom(),
                    conversationDTO.getPhoneTo(), conversationDTO.getPhoneNumberFrom(),
                    conversationDTO.getPhoneNumberTo());

            logger.infoMessage(String.format("Вызов абонента %s завершен. Разговор продлился: %s", conversationDTO.getPhoneNumberTo(), duration));
            endTime = LocalDateTime.now();

            return new CallResponseDto.Builder()
                    .startTime(startTime)
                    .endTime(endTime)
                    .timeCall(Duration.between(endTime, startTime))
                    .phoneFrom(conversationDTO.getPhoneNumberFrom())
                    .phoneTo(conversationDTO.getPhoneNumberTo())
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

    private void setAndLogStatusForWaiting(Phone phoneFrom, Phone phoneTo,
                                                  String phoneNumberFrom, String phoneNumberTo) {
        phoneFrom.setStatus(PhoneStatusEnum.WAITING);
        phoneTo.setStatus(PhoneStatusEnum.WAITING);

        phoneServiceUtils.setPhoneStatusRuntime(phoneFrom.getStatus(), phoneFrom.getId());
        phoneServiceUtils.setPhoneStatusRuntime(phoneTo.getStatus(), phoneTo.getId());

        log.info("Status: " + phoneNumberFrom + " is " + phoneFrom.getStatus());
        log.info("Status: " + phoneNumberTo + " is " + phoneTo.getStatus());
    }

    private void startAndSustainConversation(Phone phoneFrom, Phone phoneTo, String duration,
                                             String phoneNumberFrom, String phoneNumberTo) {
        phoneFrom.setStatus(PhoneStatusEnum.CONVERSATION);
        phoneTo.setStatus(PhoneStatusEnum.CONVERSATION);

        phoneServiceUtils.setPhoneStatusRuntime(phoneFrom.getStatus(), phoneFrom.getId());
        phoneServiceUtils.setPhoneStatusRuntime(phoneTo.getStatus(), phoneTo.getId());

        log.info("Status: " + phoneNumberFrom + " is " + phoneFrom.getStatus());
        log.info("Status: " + phoneNumberTo + " is " + phoneTo.getStatus());

        int durationMilliSecond = Integer.parseInt(duration);

        try {
            Thread.sleep(durationMilliSecond);
        } catch (InterruptedException e) {
            throw new PhoneException(InfMsg.CONVERSATION_WAS_INTERRUPT);
        }
    }

    private void makeCallBetweenPhones(Phone phoneFrom, Phone phoneTo, String phoneNumberFrom, String phoneNumberTo) {

        phoneFrom.setStatus(PhoneStatusEnum.CALL);
        phoneTo.setStatus(PhoneStatusEnum.CALL);

        phoneServiceUtils.setPhoneStatusRuntime(phoneFrom.getStatus(), phoneFrom.getId());
        phoneServiceUtils.setPhoneStatusRuntime(phoneTo.getStatus(), phoneTo.getId());

        log.info("Status: " + phoneNumberFrom + " is " + phoneFrom.getStatus());
        log.info("Status: " + phoneNumberTo + " is " + phoneTo.getStatus());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new PhoneException(InfMsg.CONNECTION_NOT_BE_ESTABLISHED);
        }
    }
}
