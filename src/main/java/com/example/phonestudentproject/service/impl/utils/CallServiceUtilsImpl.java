package com.example.phonestudentproject.service.impl.utils;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.service.api.utils.CallServiceUtils;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CallServiceUtilsImpl implements CallServiceUtils {

    @Override
    public String buildFullPhoneNumber(PhoneDTO phoneDtoTo) {
        String phoneNumber = phoneDtoTo.getPhoneNumber();
        String value = phoneDtoTo.getRegion().getValue();
        return value + phoneNumber;
    }
}
