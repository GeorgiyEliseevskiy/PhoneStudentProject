package com.example.phonestudentproject.service.api;

import com.example.phonestudentproject.model.DTO.RegistrationDTO;
import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.model.entity.Phone;

/**
 * Интерфейс взаимодействия с телефоном.
 */
public interface PhoneService {

    /**
     * Позвонить по номеру телефона.
     *
     * @param phoneNumberFrom - номер с которого совершается звонок.
     * @param phoneNumberTo - номер на который совершается звонок.
     * @param duration - длительность звонка.
     * @return {@link CallResponseDto} отчет о звонке
     */
    CallResponseDto call(String phoneNumberFrom, String phoneNumberTo, String duration);

    /**
     * Создать телефон.
     *
     * @param registrationDTO - {@link RegistrationDTO} - дто содержащая данные, необходимые для регистрации нового телефона.
     * @return {@link Phone}
     */
    Phone createPhoneNumber(RegistrationDTO registrationDTO);

    /**
     * Удалить телефон из базы данных по номеру.
     *
     * @param phoneNumber - номер телефона без региона.
     */
    void delete(String phoneNumber);
}
