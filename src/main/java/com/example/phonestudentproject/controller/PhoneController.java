package com.example.phonestudentproject.controller;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.model.DTO.RegistrationDTO;
import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.service.api.PhoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/phone")
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
    public PhoneDTO createPhoneNumber(@RequestParam RegistrationDTO registrationDTO) {
        return phoneService.createPhoneNumber(registrationDTO);
    }

    @PostMapping("/delete")
    @Operation(summary = "Удалить номер телефона")
    public void delete(@RequestParam String phoneNumber) {
        phoneService.delete(phoneNumber);
    }
}
