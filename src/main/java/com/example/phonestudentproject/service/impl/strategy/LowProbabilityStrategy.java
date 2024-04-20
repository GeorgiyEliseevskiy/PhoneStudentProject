package com.example.phonestudentproject.service.impl.strategy;

import com.example.phonestudentproject.service.api.strategy.ProbabilityStrategy;

public class LowProbabilityStrategy implements ProbabilityStrategy {
    @Override
    public double calculateProbability(int callLogSize) {
        return 10;
    }
}
