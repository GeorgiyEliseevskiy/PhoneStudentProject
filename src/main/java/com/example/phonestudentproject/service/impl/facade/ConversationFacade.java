package com.example.phonestudentproject.service.impl.facade;

import com.example.phonestudentproject.model.DTO.ConversationDTO;
import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.service.api.CallService;
import com.example.phonestudentproject.service.api.PhoneService;
import com.example.phonestudentproject.service.api.utils.CallServiceUtils;
import com.example.phonestudentproject.service.api.utils.PhoneServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConversationFacade {

    private final PhoneServiceUtils phoneServiceUtils;
    private final CallServiceUtils callServiceUtils;

    public ConversationDTO getConversationDTO(PhoneDTO phoneDtoTo, PhoneDTO phoneDtoFrom) {
        String phoneNumberFrom = callServiceUtils.buildFullPhoneNumber(phoneDtoFrom);
        String phoneNumberTo = callServiceUtils.buildFullPhoneNumber(phoneDtoTo);

        Phone phoneFrom = phoneServiceUtils.getPhoneByPhoneNumber(phoneDtoFrom.getPhoneNumber());
        Phone phoneTo = phoneServiceUtils.getPhoneByPhoneNumber(phoneDtoFrom.getPhoneNumber());

        return new ConversationDTO()
                .setPhoneFrom(phoneFrom)
                .setPhoneTo(phoneTo)
                .setPhoneNumberFrom(phoneNumberFrom)
                .setPhoneNumberTo(phoneNumberTo);
    }

}
