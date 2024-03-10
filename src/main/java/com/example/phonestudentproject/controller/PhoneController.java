package com.example.phonestudentproject.controller;

import com.example.phonestudentproject.model.DTO.response.CallResponseDto;
import com.example.phonestudentproject.service.api.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/phone")
public class PhoneController {

    private final PhoneService phoneService;

    @GetMapping("/call")
    public CallResponseDto call(@RequestParam String phoneNumberFrom, @RequestParam String phoneNumberTo,
                                @RequestParam String duration) {
        return phoneService.call(phoneNumberFrom, phoneNumberTo, duration);
    }

    @PostMapping("/createPhoneNumber")
    public void createPhoneNumber() {
        phoneService.createPhoneNumber();
    }

    @PostMapping("/delete")
    public void delete(@RequestParam String phoneNumber) {
        phoneService.delete(phoneNumber);
    }
}
