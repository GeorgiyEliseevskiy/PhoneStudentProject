package com.example.phonestudentproject.service.impl;

import com.example.phonestudentproject.model.DTO.PhoneDTO;
import com.example.phonestudentproject.service.api.ProbabilityService;
import com.example.phonestudentproject.service.api.strategy.ProbabilityStrategy;
import com.example.phonestudentproject.service.impl.factory.ProbabilityStrategyFactory;
import com.example.phonestudentproject.service.impl.strategy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class ProbabilityCallServiceImpl implements ProbabilityService {

    private final ProbabilityStrategyFactory strategyFactory;
    @Override
    public String getProbabilityCall(PhoneDTO phoneDtoFrom) {

       double countProbability = 0.0;

       countProbability += getProbabilityFromDate();

       countProbability += getProbabilityFromLogs(phoneDtoFrom);

        return String.valueOf(countProbability);
    }

    private double getProbabilityFromLogs(PhoneDTO phoneDtoFrom) {
        // int size = phoneDtoFrom.getLogCalls().size();

        ProbabilityCalculator calculator;

        int size = 40; //TODO Добавить логи вызовов
        double countProbability = 0.0;

        ProbabilityStrategy probabilityStrategy = strategyFactory.getStrategy(size);
        return probabilityStrategy.calculateProbability(size);
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
