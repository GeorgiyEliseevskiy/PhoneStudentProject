package com.example.phonestudentproject.service.impl;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.service.api.ProbabilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProbabilityCallServiceImpl implements ProbabilityService {
    @Override
    public String getProbabilitySerice(PhoneDTO phoneDtoFrom) {

        LocalDateTime timeNow = LocalDateTime.now();


    }
}
