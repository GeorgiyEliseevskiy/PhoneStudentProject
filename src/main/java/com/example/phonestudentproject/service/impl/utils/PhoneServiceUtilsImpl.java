package com.example.phonestudentproject.service.impl.utils;

import com.example.phonestudentproject.exception.InfMsg;
import com.example.phonestudentproject.exception.PhoneException;
import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.repository.PhoneRepository;
import com.example.phonestudentproject.service.api.utils.PhoneServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhoneServiceUtilsImpl implements PhoneServiceUtils {

    private final PhoneRepository phoneRepository;

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
    public void setPhoneStatusRuntime(PhoneStatusEnum status, Long id) {
        phoneRepository.updatePhoneStatus(status, id);
    }
}
