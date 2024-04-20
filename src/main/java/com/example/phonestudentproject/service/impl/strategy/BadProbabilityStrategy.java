package com.example.phonestudentproject.service.impl.strategy;

import com.example.phonestudentproject.service.api.strategy.ProbabilityStrategy;

public class BadProbabilityStrategy implements ProbabilityStrategy {
    @Override
    public double calculateProbability(int callLogSize) {
        return 5;
    }
}
