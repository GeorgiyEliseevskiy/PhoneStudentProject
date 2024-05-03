package com.example.phonestudentproject.controller;

import com.example.phonestudentproject.model.DTO.RegistrationDTO;
import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.model.entity.Phone;
import com.example.phonestudentproject.service.api.PhoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для взаимодействия с API телефон.
 */
@RestController
@RequestMapping("/phone")
@AllArgsConstructor
@Tag(name = "API Взаимодействие с телеофоном")
public class PhoneController {

    private final PhoneService phoneService;

    @PutMapping("/call")
    @Operation(summary = "Позвонить по номеру телефона")
    public CallResponseDto call(@RequestParam String phoneNumberFrom, @RequestParam String phoneNumberTo,
                                @RequestParam String duration) {
        return phoneService.call(phoneNumberFrom, phoneNumberTo, duration);
    }

    @PostMapping("/createPhoneNumber")
    @Operation(summary = "Создать телефон")
    @Transactional
    public Phone createPhoneNumber(@RequestBody RegistrationDTO registrationDTO) {
        return phoneService.createPhoneNumber(registrationDTO);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Удалить номер телефона")
    public void delete(@RequestParam String phoneNumber) {
        phoneService.delete(phoneNumber);
    }
}
