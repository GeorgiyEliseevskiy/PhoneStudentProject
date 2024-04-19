package com.example.phonestudentproject.service.impl;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.service.api.ProbabilityService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ProbabilityCallServiceImpl implements ProbabilityService {

    @Override
    public String getProbabilityCall(PhoneDTO phoneDtoFrom) {

       double countProbability = 0.0;

       countProbability += getProbabilityFromDate();

       countProbability += getProbabilityFromLogs(phoneDtoFrom);

        return String.valueOf(countProbability);
    }

    private double getProbabilityFromLogs(PhoneDTO phoneDtoFrom) {
        // int size = phoneDtoFrom.getLogCalls().size();
        int size = 25; //TODO Добавить логи вызовов
        double countProbability = 0.0;

        if (size > 10 && size < 20) {
           countProbability += 10;
       } else if (size > 20 && size < 30) {
            countProbability +=15;
        } else if (size > 30 && size < 40) {
            countProbability +=20;
        } else {
            countProbability +=5;
        }
        return countProbability;
    }

    private Double getProbabilityFromDate() {
        double probability = 0.0;
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        if (currentTime.isAfter(LocalTime.of(8, 0)) && currentTime.isBefore(LocalTime.of(23, 0))) {
            probability += 40;
        } else {
            probability += 10;
        }

        if (DayOfWeek.FRIDAY.equals(dayOfWeek) || DayOfWeek.SATURDAY.equals(dayOfWeek)
                || DayOfWeek.SUNDAY.equals(dayOfWeek)) {
            probability += 20;
        } else {
            probability += 10;
        }

        return probability;
    }
}
